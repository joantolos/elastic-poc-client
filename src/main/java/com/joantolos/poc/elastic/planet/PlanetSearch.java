package com.joantolos.poc.elastic.planet;

import com.joantolos.poc.elastic.engine.ElasticSearchEngine;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonArray;
import java.io.StringReader;

public class PlanetSearch {

    private final String INDEX = "planets";
    private ElasticSearchEngine elasticSearchEngine;

    public PlanetSearch(){
        this.elasticSearchEngine = new ElasticSearchEngine();
    }

    public String searchPlanet(String query) {
        String searchResponse = elasticSearchEngine.search(INDEX, query);

        JsonReader reader = Json.createReader(new StringReader((searchResponse)));
        JsonArray jsonArray = reader.readObject().getJsonArray("planet");
        return jsonArray.toString();
    }
}
