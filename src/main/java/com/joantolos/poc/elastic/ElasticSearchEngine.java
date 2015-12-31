package com.joantolos.poc.elastic;

import com.joantolos.poc.elastic.remote.ElasticServer;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import org.apache.commons.lang.StringUtils;

import javax.json.Json;
import java.io.StringReader;

public class ElasticSearchEngine {

    private final String ELASTIC_URL = "https://maple-563900.us-east-1.bonsai.io";
    private final String ELASTIC_USER = "4udfe4e3";
    private final String ELASTIC_PASSWORD = "8p85z4at2nxnhvsa";
    private ElasticServer elasticServer;

    public ElasticSearchEngine(){
        if(!StringUtils.isEmpty(ELASTIC_USER) && !StringUtils.isEmpty(ELASTIC_PASSWORD)){
            elasticServer = Feign.builder()
                    .requestInterceptor(new BasicAuthRequestInterceptor(ELASTIC_USER, ELASTIC_PASSWORD))
                    .target(ElasticServer.class, ELASTIC_URL);
        }else{
            elasticServer = Feign.builder()
                    .target(ElasticServer.class, ELASTIC_URL);
        }
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
                               .getJsonObject("_source")
                               .getJsonArray("planet").toString();
    }
}
