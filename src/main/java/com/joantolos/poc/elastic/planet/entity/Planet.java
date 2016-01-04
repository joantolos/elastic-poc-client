package com.joantolos.poc.elastic.planet.entity;

import java.io.Serializable;

public class Planet implements Serializable  {

    private Long id;
    private String name;
    private Details details;

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

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}
