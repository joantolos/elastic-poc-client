package com.joantolos.poc.elastic;

import com.joantolos.poc.elastic.entity.Planet;
import com.joantolos.poc.elastic.utils.JsonUtils;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlanetTest {

    private ElasticSearchEngine elasticSearchEngine;
    private String queryBody;

    @Before
    public void setUp(){
        this.elasticSearchEngine = new ElasticSearchEngine();
        this.queryBody =
                        "{\n" +
                        "    \"query\" : {\n" +
                        "        \"term\" : { \"planet.id\" : \"1\" }\n" +
                        "    }\n" +
                        "}";
    }

    @After
    public void tearDown(){
        this.elasticSearchEngine = null;
    }

    @Test
    public void searchTest(){
        String planetListJson = this.elasticSearchEngine.search("planets", queryBody);
        Planet planet = (Planet) JsonUtils.jsonToObject(planetListJson, Planet.class);
        Assert.assertNotNull(planet);
        Assert.assertEquals(new Long(1), planet.getId());
        Assert.assertEquals("Mercury", planet.getName());
    }
}
