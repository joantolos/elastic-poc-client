package com.joantolos.poc.elastic.planet.entity;

import java.io.Serializable;

public class Details implements Serializable {

    private Measurement volume;
    private Measurement mass;
    private Measurement density;

    public Measurement getVolume() {
        return volume;
    }

    public void setVolume(Measurement volume) {
        this.volume = volume;
    }

    public Measurement getMass() {
        return mass;
    }

    public void setMass(Measurement mass) {
        this.mass = mass;
    }

    public Measurement getDensity() {
        return density;
    }

    public void setDensity(Measurement density) {
        this.density = density;
    }
}
