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

    public static void main (String[] args) {
        String mappings = FileUtils.streamToString(ElasticPocClient.class.getClassLoader().getResourceAsStream("elastic"+File.separator+"planetsIndexCreation.json"));

        log.info("Deleting index: "+index);
        elasticSearchEngine.delete(index);
        log.info("Creating mappings...");
        elasticSearchEngine.mappings(index, mappings);

        playWithPlanets();
    }

    public static void playWithPlanets(){
        PlanetSearch planetSearch = new PlanetSearch();
        String planetById = FileUtils.streamToString(ElasticPocClient.class.getClassLoader().getResourceAsStream("elastic" + File.separator + "query" + File.separator + "planetById.json"));
        String planetByName = FileUtils.streamToString(ElasticPocClient.class.getClassLoader().getResourceAsStream("elastic" + File.separator + "query" + File.separator + "planetByName.json"));
        String planet = FileUtils.streamToString(ElasticPocClient.class.getClassLoader().getResourceAsStream("elastic" + File.separator + "query" + File.separator + "planet.json"));
        List<Planet> planets;

        String mercuryBulk = FileUtils.streamToStringWithNewLineChar(ElasticPocClient.class.getClassLoader().getResourceAsStream("planets"+File.separator+"mercury.bulk"));
        elasticSearchEngine.bulk(index, mercuryBulk);
        log.info("Mercury Added");

        String venusBulk = FileUtils.streamToStringWithNewLineChar(ElasticPocClient.class.getClassLoader().getResourceAsStream("planets"+File.separator+"venus.bulk"));
        elasticSearchEngine.bulk(index, venusBulk);
        log.info("Venus Added");

        String earthBulk = FileUtils.streamToStringWithNewLineChar(ElasticPocClient.class.getClassLoader().getResourceAsStream("planets"+File.separator+"earth.bulk"));
        elasticSearchEngine.bulk(index, earthBulk);
        log.info("Earth Added");

        log.info("Looking for planet with id 3...");
        planets = JsonUtils.marshallPlanetJson(planetSearch.searchPlanet(planetById.replace("#planetId", "3")));
        log.info("Planet 3: \n"+JsonUtils.objectToJson(planets.get(0)));

        log.info("Looking for Venus...");
        planets = JsonUtils.marshallPlanetJson(planetSearch.searchPlanet(planetByName.replace("#planetName", "Venus")));
        log.info("Venus: \n"+JsonUtils.objectToJson(planets.get(0)));

        planets = JsonUtils.marshallPlanetJson(planetSearch.searchPlanet(planet.replace("#q", "{ \"aggs\" : { \"max_price\" : { \"max\" : { \"field\" : \"planet.details.volume.value\" } } } }")));
        log.info("Which has more mass, Mercury, Venus or Earth? \n"+JsonUtils.objectToJson(planets.get(0)));
    }

}
