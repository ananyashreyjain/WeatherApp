package com.Sapient.WeatherApp.models;

public class Request {

    final public static String URL = "https://api.openweathermap.org/data/2.5/forecast";

    final public static String appid = "d2929e9483efc82c82c32ee7e02d563e";

    final public static String cnt = Integer.toString(3);

    final public String location;

    final public String userName;

    public Request(String userName, String location){
        this.userName = userName;
        this.location = location;
    }
} 