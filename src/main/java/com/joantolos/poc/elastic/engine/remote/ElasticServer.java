package com.joantolos.poc.elastic.engine.remote;

import feign.Body;
import feign.Param;
import feign.RequestLine;

public interface ElasticServer {

    @RequestLine("DELETE /{index}")
    String delete(@Param("index") String index);

    @RequestLine("DELETE /{index}/_query")
    @Body("{body}")
    String deleteByQuery(@Param("index") String index, @Param("body") String body);

    @RequestLine("PUT /{index}")
    @Body("{body}")
    String mappings(@Param("index") String index, @Param("body") String body);

    @RequestLine("POST /{index}/_bulk")
    @Body("{body}")
    String bulk(@Param("index") String index, @Param("body") String body);

    @RequestLine("POST /{index}/_search")
    @Body("{body}")
    String search(@Param("index") String index, @Param("body") String body);
}
