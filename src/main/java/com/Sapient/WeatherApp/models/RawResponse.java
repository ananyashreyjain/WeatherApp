package com.Sapient.WeatherApp.models;

import java.util.ArrayList;

/**
 * Model to store relevant response data for further processing
 */
public class RawResponse {

    public final ArrayList<String> maxTemp;
    public final ArrayList<String> minTemp;
    public final ArrayList<String> windSpeed;
    public final ArrayList<String> weather;
    private Boolean cachedData;

    public RawResponse(){
        this.maxTemp = new ArrayList<>();
        this.minTemp = new ArrayList<>();
        this.windSpeed = new ArrayList<>();
        this.weather = new ArrayList<>();
        this.cachedData = false;
    }

    public void cached(){
        this.cachedData = true;
    }

    public Boolean isCached(){
        return this.cachedData;
    }
}
