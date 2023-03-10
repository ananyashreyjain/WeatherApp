package com.Sapient.WeatherApp.models;

import java.util.ArrayList;

public class RawResponse {

    public final ArrayList<String> maxTemp;
    public final ArrayList<String> minTemp;
    public final ArrayList<String> windSpeed;
    public final ArrayList<String> weather; 

    public RawResponse(){
        this.maxTemp = new ArrayList<>();
        this.minTemp = new ArrayList<>();
        this.windSpeed = new ArrayList<>();
        this.weather = new ArrayList<>();
    }
}
