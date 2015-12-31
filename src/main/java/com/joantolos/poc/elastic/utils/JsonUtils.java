package com.joantolos.poc.elastic.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joantolos.poc.elastic.entity.Planet;

import java.util.ArrayList;

public class JsonUtils {

    public static String objectToJson(Object o){
        return new Gson().toJson( o );
    }

    public static Object jsonToObject(String json, Class c){
        return new Gson().fromJson( json, c );
    }

    public static ArrayList<Planet> marshallPlanetJson(String planetListJSON){
        return new Gson().fromJson(planetListJSON, new TypeToken<ArrayList<Planet>>() { }.getType());
    }
}
