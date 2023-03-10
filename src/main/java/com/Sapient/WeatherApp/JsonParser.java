package com.Sapient.WeatherApp;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import com.Sapient.WeatherApp.models.RawResponse;
import java.util.Iterator;

public class JsonParser{
    public static RawResponse parse(String response){
        JSONParser parser = new JSONParser();
        RawResponse rawResponse = new RawResponse();
        try{
            JSONObject json = (JSONObject) parser.parse(response);
            JSONArray jarray = (JSONArray)json.get("list");
            Iterator days = jarray.iterator();
            while (days.hasNext()) {
                JSONObject day = (JSONObject) days.next();
                JSONObject temperature = (JSONObject) day.get("main");
                JSONObject wind = (JSONObject) day.get("wind");
                Iterator weathers = ((JSONArray) day.get("weather")).iterator();
                while(weathers.hasNext()){
                    JSONObject weather = (JSONObject) weathers.next();
                    String main = weather.get("main").toString();
                    rawResponse.weather.add(main);
                }
                rawResponse.maxTemp.add(temperature.get("temp_max").toString());
                rawResponse.minTemp.add(temperature.get("temp_min").toString());
                rawResponse.windSpeed.add(wind.get("speed").toString());
            }
        } catch (Exception exception){
            System.out.println(exception);
        }
        return rawResponse;
    }
}