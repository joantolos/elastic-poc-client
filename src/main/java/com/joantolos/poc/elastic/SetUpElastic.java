package com.joantolos.poc.elastic;

import com.joantolos.poc.elastic.engine.ElasticSearchEngine;
import com.joantolos.poc.elastic.utils.FileUtils;

public class SetUpElastic {

    public static void main (String[] args) {

        ElasticSearchEngine elasticSearchEngine = new ElasticSearchEngine();
        String index = "planets";
        String mappings = FileUtils.streamToString(SetUpElastic.class.getClassLoader().getResourceAsStream("planetsIndexCreation.json"));
        String bulk = FileUtils.streamToStringWithNewLineChar(SetUpElastic.class.getClassLoader().getResourceAsStream("firstTwoPlanets.bulk"));

        elasticSearchEngine.delete(index);
        elasticSearchEngine.mappings(index, mappings);
        elasticSearchEngine.bulk(index, bulk);
    }

}
