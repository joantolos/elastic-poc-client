package com.joantolos.poc.elastic.planet.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Measurement implements Serializable {

    private String unit;
    private Double value;

}
