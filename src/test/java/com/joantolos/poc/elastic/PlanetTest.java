package com.joantolos.poc.elastic;

import com.joantolos.poc.elastic.entity.Planet;
import com.joantolos.poc.elastic.utils.JsonUtils;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        List<Planet> planets = JsonUtils.marshallPlanetJson(planetListJson);
        Assert.assertNotNull(planets);
        Assert.assertTrue(!planets.isEmpty());
        Assert.assertEquals(new Long(1), planets.get(0).getId());
        Assert.assertEquals("Mercury", planets.get(0).getName());
        Assert.assertEquals("Mercury", planets.get(0).getMythology().getName());
        Assert.assertEquals("Greco-Roman", planets.get(0).getMythology().getOrigin());
    }
}
