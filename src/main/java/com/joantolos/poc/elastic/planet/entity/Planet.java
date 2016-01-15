package com.joantolos.poc.elastic.planet.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Planet implements Serializable  {

    private Long id;
    private String name;
    private Double distanceFromSun;
    private Details details;

}
