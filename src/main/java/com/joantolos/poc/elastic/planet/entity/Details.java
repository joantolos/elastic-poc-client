package com.joantolos.poc.elastic.planet.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Details implements Serializable {

    private Measurement volume;
    private Measurement mass;
    private Measurement density;

}
