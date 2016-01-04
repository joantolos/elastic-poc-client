package com.joantolos.poc.elastic;

import com.joantolos.poc.elastic.engine.ElasticSearchEngine;
import com.joantolos.poc.elastic.utils.FileUtils;

import java.io.File;

public class ElasticPocClient {

    static ElasticSearchEngine elasticSearchEngine = new ElasticSearchEngine();
    static String index = "planets";

    public static void main (String[] args) {
        String mappings = FileUtils.streamToString(ElasticPocClient.class.getClassLoader().getResourceAsStream("elastic"+File.separator+"planetsIndexCreation.json"));
        String mercuryBulk = FileUtils.streamToStringWithNewLineChar(ElasticPocClient.class.getClassLoader().getResourceAsStream("planets"+File.separator+"mercury.bulk"));
        String venusBulk = FileUtils.streamToStringWithNewLineChar(ElasticPocClient.class.getClassLoader().getResourceAsStream("planets"+File.separator+"venus.bulk"));

        elasticSearchEngine.delete(index);
        elasticSearchEngine.mappings(index, mappings);
        elasticSearchEngine.bulk(index, mercuryBulk);
        elasticSearchEngine.bulk(index, venusBulk);

//        playWithPlanets();
    }

    public static void playWithPlanets(){
        //Adding Earth
        String earthBulk = FileUtils.streamToStringWithNewLineChar(ElasticPocClient.class.getClassLoader().getResourceAsStream("planets"+File.separator+"earth.bulk"));
        elasticSearchEngine.bulk(index, earthBulk);
    }

}
