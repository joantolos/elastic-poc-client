package com.joantolos.poc.elastic.planet.entity;

import java.io.Serializable;

public class Mythology implements Serializable {

    private String origin;
    private String name;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
