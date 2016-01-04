package com.joantolos.poc.elastic;

import com.joantolos.poc.elastic.engine.ElasticSearchEngine;
import com.joantolos.poc.elastic.utils.FileUtils;
import org.junit.*;

import java.io.IOException;

public class ElasticSearchEngineTest {

    private ElasticSearchEngine elasticSearchEngine;
    private String index;
    private String mappings;
    private String bulk;

    @Before
    public void setUp() {
        this.elasticSearchEngine = new ElasticSearchEngine();
        this.index = "planets";
        this.mappings = FileUtils.streamToString(getClass().getClassLoader().getResourceAsStream("planetsIndexCreation.json"));
        this.bulk = FileUtils.streamToStringWithNewLineChar(getClass().getClassLoader().getResourceAsStream("mercuryAndVenus.bulk"));
    }

    @After
    public void tearDown(){
        this.elasticSearchEngine = null;
    }

    @Test
    @Ignore
    public void deleteTest() {
        String elasticResponse = this.elasticSearchEngine.delete(this.index);
        Assert.assertNotNull(elasticResponse);
        Assert.assertEquals("{\"acknowledged\":true}", elasticResponse);
    }

    @Test
    @Ignore
    public void mappingsTest() {
        String elasticResponse = this.elasticSearchEngine.mappings(this.index, this.mappings);
        Assert.assertNotNull(elasticResponse);
        Assert.assertEquals("{\"acknowledged\":true}", elasticResponse);
    }

    @Test
    @Ignore
    public void bulkTest() throws IOException {
        String elasticResponse = this.elasticSearchEngine.bulk(this.index, this.bulk);
        Assert.assertNotNull(elasticResponse);
    }
}
