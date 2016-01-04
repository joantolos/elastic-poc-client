package com.joantolos.poc.elastic.planet.entity;

import java.io.Serializable;

public class Measurement implements Serializable {

    private String unit;
    private Double value;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
