package com.framboos.socialweather.socialweather.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WeatherPost {
    public String imageURL;
    public String temperature;
    public int weatherType;
    public String profileURL;
    public String profileName;
    public String location;
    public String likes;
    public ArrayList<ArrayList<String>> comments = new ArrayList<>();

    public WeatherPost(String imageURL) {
        this.imageURL = imageURL;
    }
}
