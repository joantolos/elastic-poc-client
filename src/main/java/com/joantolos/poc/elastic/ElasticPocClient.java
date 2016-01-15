package com.joantolos.poc.elastic;

import com.joantolos.poc.elastic.engine.ElasticSearchEngine;
import com.joantolos.poc.elastic.planet.PlanetSearch;
import com.joantolos.poc.elastic.planet.entity.Planet;
import com.joantolos.poc.elastic.utils.FileUtils;
import com.joantolos.poc.elastic.utils.JsonUtils;
import lombok.extern.java.Log;

import java.io.File;
import java.util.List;

@Log
public class ElasticPocClient {

    static ElasticSearchEngine elasticSearchEngine = new ElasticSearchEngine();
    static String index = "planets";

    public static void main (String[] args) throws InterruptedException {
        String mappings = FileUtils.streamToString(ElasticPocClient.class.getClassLoader().getResourceAsStream("elastic"+File.separator+"planetsIndexCreation.json"));

        log.info("Deleting index: "+index);
        elasticSearchEngine.delete(index);
        log.info("Creating mappings...");
        elasticSearchEngine.mappings(index, mappings);

        playWithPlanets();
    }

    public static void playWithPlanets() throws InterruptedException {
        PlanetSearch planetSearch = new PlanetSearch();
        String planetById = FileUtils.streamToString(ElasticPocClient.class.getClassLoader().getResourceAsStream("elastic" + File.separator + "query" + File.separator + "planetById.json"));
        String planetByName = FileUtils.streamToString(ElasticPocClient.class.getClassLoader().getResourceAsStream("elastic" + File.separator + "query" + File.separator + "planetByName.json"));
        List<Planet> planets;

        String mercuryBulk = FileUtils.streamToStringWithNewLineChar(ElasticPocClient.class.getClassLoader().getResourceAsStream("planets"+File.separator+"mercury.bulk"));
        elasticSearchEngine.bulk(index, mercuryBulk);
        log.info("Mercury Added");

        log.info("Sleeping 1000 miliseconds, because this Elastic Search is slow and the bulks need a little time to be posted...");
        Thread.sleep(1000);

        String venusBulk = FileUtils.streamToStringWithNewLineChar(ElasticPocClient.class.getClassLoader().getResourceAsStream("planets"+File.separator+"venus.bulk"));
        elasticSearchEngine.bulk(index, venusBulk);
        log.info("Venus Added");

        log.info("Sleeping 1000 miliseconds, because this Elastic Search is slow and the bulks need a little time to be posted...");
        Thread.sleep(1000);

        String earthBulk = FileUtils.streamToStringWithNewLineChar(ElasticPocClient.class.getClassLoader().getResourceAsStream("planets"+File.separator+"earth.bulk"));
        elasticSearchEngine.bulk(index, earthBulk);
        log.info("Earth Added");

        log.info("Sleeping 1000 miliseconds, because this Elastic Search is slow and the bulks need a little time to be posted...");
        Thread.sleep(2000);

        log.info("Looking for planet with id 3...");
        planets = JsonUtils.marshallPlanetJson(planetSearch.searchPlanet(planetById.replace("#planetId", "3")));
        log.info("Planet 3: \n"+JsonUtils.objectToJson(planets.get(0)));

        log.info("Looking for Venus...");
        planets = JsonUtils.marshallPlanetJson(planetSearch.searchPlanet(planetByName.replace("#planetName", "Venus")));
        log.info("Venus: \n"+JsonUtils.objectToJson(planets.get(0)));

        planets = JsonUtils.marshallPlanetJson(planetSearch.searchPlanet("{ \"sort\" : [{ \"planet.id\" : { \"order\" : \"desc\" } } ], \"size\" : 1 }"));
        log.info("Which one is farther from the Sun, Mercury, Venus or Earth? \n"+planets.get(0).getName());

        insertAllOtherPlanets();

        planets = JsonUtils.marshallPlanetJson(planetSearch.searchPlanet("{ \"sort\" : [{ \"planet.id\" : { \"order\" : \"desc\" } } ], \"size\" : 1 }"));
        log.info("Which planet is farther from the Sun? \n"+planets.get(0).getName());

        log.info("Now we delete Neptune, because Neil deGrasse Tyson hates little planets...\n"+planets.get(0).getName());
        elasticSearchEngine.deleteByQuery("planets", "{ \"query\" : { \"term\" : { \"planet.id\" : \"8\" } } }");
        log.info("Sleeping 1000 miliseconds, because this Elastic Search is slow and the bulks need a little time to be posted...");
        Thread.sleep(2000);

        planets = JsonUtils.marshallPlanetJson(planetSearch.searchPlanet("{ \"sort\" : [{ \"planet.id\" : { \"order\" : \"desc\" } } ], \"size\" : 1 }"));
        log.info("Which planet is farther from the Sun now? \n"+planets.get(0).getName());
    }

    private static void insertAllOtherPlanets() throws InterruptedException {
        String jupiterBulk = FileUtils.streamToStringWithNewLineChar(ElasticPocClient.class.getClassLoader().getResourceAsStream("planets"+File.separator+"jupiter.bulk"));
        elasticSearchEngine.bulk(index, jupiterBulk);
        log.info("Jupiter Added");
        log.info("Sleeping 1000 miliseconds, because this Elastic Search is slow and the bulks need a little time to be posted...");
        Thread.sleep(2000);
        String marsBulk = FileUtils.streamToStringWithNewLineChar(ElasticPocClient.class.getClassLoader().getResourceAsStream("planets"+File.separator+"mars.bulk"));
        elasticSearchEngine.bulk(index, marsBulk);
        log.info("Mars Added");
        log.info("Sleeping 1000 miliseconds, because this Elastic Search is slow and the bulks need a little time to be posted...");
        Thread.sleep(2000);
        String neptuneBulk = FileUtils.streamToStringWithNewLineChar(ElasticPocClient.class.getClassLoader().getResourceAsStream("planets"+File.separator+"neptune.bulk"));
        elasticSearchEngine.bulk(index, neptuneBulk);
        log.info("Neptune Added");
        log.info("Sleeping 1000 miliseconds, because this Elastic Search is slow and the bulks need a little time to be posted...");
        Thread.sleep(2000);
        String saturnBulk = FileUtils.streamToStringWithNewLineChar(ElasticPocClient.class.getClassLoader().getResourceAsStream("planets"+File.separator+"saturn.bulk"));
        elasticSearchEngine.bulk(index, saturnBulk);
        log.info("Saturn Added");
        log.info("Sleeping 1000 miliseconds, because this Elastic Search is slow and the bulks need a little time to be posted...");
        Thread.sleep(2000);
        String uranusBulk = FileUtils.streamToStringWithNewLineChar(ElasticPocClient.class.getClassLoader().getResourceAsStream("planets"+File.separator+"uranus.bulk"));
        elasticSearchEngine.bulk(index, uranusBulk);
        log.info("Uranus Added");
        log.info("Sleeping 1000 miliseconds, because this Elastic Search is slow and the bulks need a little time to be posted...");
        Thread.sleep(2000);
    }

}
