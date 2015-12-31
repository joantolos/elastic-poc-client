package com.joantolos.poc.elastic.entity;

import java.io.Serializable;

public class Planet implements Serializable  {

    private Long id;
    private String name;
    private Mythology mythology;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mythology getMythology() {
        return mythology;
    }

    public void setMythology(Mythology mythology) {
        this.mythology = mythology;
    }
}
