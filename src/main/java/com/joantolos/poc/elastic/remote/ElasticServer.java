package com.joantolos.poc.elastic.remote;

import feign.Body;
import feign.Param;
import feign.RequestLine;

public interface ElasticServer {

    @RequestLine("POST /{index}/_search")
    @Body("{body}")
    String search(@Param("index") String index, @Param("body") String body);
}
