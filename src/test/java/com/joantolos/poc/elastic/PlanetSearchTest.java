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
        Assert.assertEquals("5.43 g/cm^3", planets.get(0).getDetails().getDensity());
        Assert.assertEquals("3.302×10^23 km^3", planets.get(0).getDetails().getMass());
        Assert.assertEquals("6.083×10^10 km^3", planets.get(0).getDetails().getVolume());
    }

    @Test
    public void searchPlanet2Test(){
        String planetListJson = this.planetSearch.searchPlanet(planetById.replace("#planetId", "2"));
        List<Planet> planets = JsonUtils.marshallPlanetJson(planetListJson);
        Assert.assertNotNull(planets);
        Assert.assertTrue(!planets.isEmpty());
        Assert.assertEquals(new Long(2), planets.get(0).getId());
        Assert.assertEquals("Venus", planets.get(0).getName());
        Assert.assertEquals("5.24 g/cm^3", planets.get(0).getDetails().getDensity());
        Assert.assertEquals("4.8690×10^24 km^3", planets.get(0).getDetails().getMass());
        Assert.assertEquals("9.28×10^11 km^3", planets.get(0).getDetails().getVolume());
    }

}
