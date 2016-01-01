package com.joantolos.poc.elastic;

import com.joantolos.poc.elastic.planet.entity.Planet;
import com.joantolos.poc.elastic.planet.PlanetSearch;
import com.joantolos.poc.elastic.utils.FileUtils;
import com.joantolos.poc.elastic.utils.JsonUtils;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PlanetSearchTest {

    private PlanetSearch planetSearch;
    private String planetById;

    @Before
    public void setUp() {
        this.planetSearch = new PlanetSearch();
        this.planetById = FileUtils.streamToString(getClass().getClassLoader().getResourceAsStream("query/planetById.json"));
    }

    @After
    public void tearDown(){
        this.planetSearch = null;
    }

    @Test
    public void searchPlanet1Test(){
        String planetListJson = this.planetSearch.searchPlanet(planetById.replace("#planetId", "1"));
        List<Planet> planets = JsonUtils.marshallPlanetJson(planetListJson);
        Assert.assertNotNull(planets);
        Assert.assertTrue(!planets.isEmpty());
        Assert.assertEquals(new Long(1), planets.get(0).getId());
        Assert.assertEquals("Mercury", planets.get(0).getName());
        Assert.assertEquals("Mercury", planets.get(0).getMythology().getName());
        Assert.assertEquals("Greco-Roman", planets.get(0).getMythology().getOrigin());
    }

    @Test
    public void searchPlanet2Test(){
        String planetListJson = this.planetSearch.searchPlanet(planetById.replace("#planetId", "2"));
        List<Planet> planets = JsonUtils.marshallPlanetJson(planetListJson);
        Assert.assertNotNull(planets);
        Assert.assertTrue(!planets.isEmpty());
        Assert.assertEquals(new Long(2), planets.get(0).getId());
        Assert.assertEquals("Venus", planets.get(0).getName());
        Assert.assertEquals("Venus", planets.get(0).getMythology().getName());
        Assert.assertEquals("Greco-Roman", planets.get(0).getMythology().getOrigin());
    }

}