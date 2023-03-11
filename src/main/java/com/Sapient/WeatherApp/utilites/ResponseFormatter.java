package com.Sapient.WeatherApp.utilites;

import com.Sapient.WeatherApp.models.RawResponse;
import com.Sapient.WeatherApp.models.Response;

public class ResponseFormatter {

    private static final String HIGH_TEMP_COMMENT = "Use sunscreen lotion";
    private static final String RAIN_PREDICTION_COMMENT = "Carry umbrella";
    private static final String HIGH_WIND_COMMENT = "It’s too windy, watch out!";
    private static final Double HIGH_WIND_SPEED = 10.0;
    private static final Double HIGH_TEMP = 313.0;
    
    private static String checkTemp(final RawResponse rawResponse){
        if(rawResponse.maxTemp.size()>0 && 
            Double.valueOf(rawResponse.maxTemp.get(0)) >= HIGH_TEMP){
            return HIGH_TEMP_COMMENT;
        }
        return null;
    }

    private static String checkRain(final RawResponse rawResponse){
        for(int i=0;i<rawResponse.weather.size();i++){
            if(rawResponse.weather.get(i).toLowerCase() == "rain"){
                return RAIN_PREDICTION_COMMENT;
            }
        }
        return null;
    }

    private static String checkWinds(final RawResponse rawResponse){
        if(rawResponse.windSpeed.size()>0 && 
            Double.valueOf(rawResponse.windSpeed.get(0))>=HIGH_WIND_SPEED){
            return HIGH_WIND_COMMENT;
        }
        return null;
    }

    private static String isCached(final RawResponse rawResponse){
        if(rawResponse.isCached()) {
            return "Found in cache";
        }
        return "Not found in cache. Network call was made";
    }

    private static String[] addTemp(final RawResponse rawResponse){
        Integer size = Math.max(rawResponse.maxTemp.size(), rawResponse.minTemp.size());
        String[] resp = new String[size];
        for(int i=0;i<size;i++){
            resp[i] = "Temperature Day " + Integer.valueOf(i) + " => ";
            resp[i] += "Min:";
            if(i<rawResponse.minTemp.size()){
                resp[i] += rawResponse.minTemp.get(i);
            }
            resp[i] += ", ";
            resp[i] += "Max:";
            if(i<rawResponse.maxTemp.size()){
                resp[i] += rawResponse.maxTemp.get(i);
            }
        }
        return resp;
    }

    public static Response doFormatting(final RawResponse rawResponse){
        String commentTemp = checkTemp(rawResponse);
        String commentRain = checkRain(rawResponse);
        String commentWinds = checkWinds(rawResponse);
        String commentCached = isCached(rawResponse);
        String[] temperatures = addTemp(rawResponse);
        String[] output = new String[temperatures.length + 4];
        for(int i=0;i<temperatures.length;i++){
            output[i] = temperatures[i];
        }
        output[temperatures.length] = commentTemp;
        output[temperatures.length+1] = commentRain;
        output[temperatures.length+2] = commentWinds;
        output[temperatures.length+3] = commentCached;
        return new Response(output);
    }

}
