package com.framboos.socialweather.socialweather.utils;

public class WeatherPost {
    public String imageURL;
    public String temperature;
    public int weatherType;
    public String profileURL;
    public String profileName;
    public String location;
    public String likes;

    public WeatherPost(String imageURL) {
        this.imageURL = imageURL;
    }
}
