package com.joantolos.poc.elastic.engine;

import com.joantolos.poc.elastic.engine.remote.ElasticServer;
import feign.Feign;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import org.apache.commons.lang.StringUtils;

import javax.json.Json;
import java.io.StringReader;

public class ElasticSearchEngine {

    //kJ0lZOgk80yD78Czt217DgDw5BOg7GES:@elasticsandbox.east-us.azr.facetflow.io
    private final String ELASTIC_URL = "https://elasticsandbox.east-us.azr.facetflow.io";
    private final String ELASTIC_USER = "kJ0lZOgk80yD78Czt217DgDw5BOg7GES";
    private final String ELASTIC_PASSWORD = "";
    private ElasticServer elasticServer;

    public ElasticSearchEngine(){
        if(!StringUtils.isEmpty(ELASTIC_USER)){
            elasticServer = Feign.builder()
                    .requestInterceptor(new BasicAuthRequestInterceptor(ELASTIC_USER, ELASTIC_PASSWORD))
                    .target(ElasticServer.class, ELASTIC_URL);
        } else {
            elasticServer = Feign.builder()
                    .target(ElasticServer.class, ELASTIC_URL);
        }
    }

    public String delete(String index){
        return elasticServer.delete(index);
    }

    public String deleteByQuery(String index, String query){
        return elasticServer.deleteByQuery(index, query);
    }

    public String mappings(String index, String mappings){
        return elasticServer.mappings(index, mappings);
    }

    public String bulk(String index, String bulk){
        return elasticServer.bulk(index, bulk);
    }

    public String search(String index, String query) {
        String searchResponse = elasticServer.search(index,query);
        return Json.createReader(
                new StringReader(
                        Json.createReader(
                                new StringReader(searchResponse))
                                .readObject().getJsonObject("hits")
                                .getJsonArray("hits")
                                .getJsonObject(0).toString())).readObject()
                                .getJsonObject("_source").toString();
    }
}
