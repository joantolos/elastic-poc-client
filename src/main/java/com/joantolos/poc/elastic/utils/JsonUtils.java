package com.joantolos.poc.elastic.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joantolos.poc.elastic.planet.entity.Planet;

import java.util.ArrayList;

public class JsonUtils {

    public static ArrayList<Planet> marshallPlanetJson(String planetListJSON){
        return new Gson().fromJson(planetListJSON, new TypeToken<ArrayList<Planet>>() { }.getType());
    }
}
