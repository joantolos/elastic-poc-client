package com.joantolos.poc.elastic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Planet {

    Long id;
    String name;
    Mythology mythology;

}
