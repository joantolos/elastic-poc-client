package com.joantolos.poc.elastic.planet.entity;

import java.io.Serializable;

public class Details implements Serializable {

    private String volume;
    private String mass;
    private String density;

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }
}
