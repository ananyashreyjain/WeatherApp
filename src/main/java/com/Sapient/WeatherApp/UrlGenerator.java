package com.Sapient.WeatherApp;

import com.Sapient.WeatherApp.models.Request;

public class UrlGenerator {
    public static String generateUrl(final Request request){

        final String generatedUrl = request.URL + "?" 
        + "appid" + "=" + request.appid
        + "&" + "q" + "=" + request.location 
        + "&" + "cnt" + "=" + request.cnt;

        System.out.println(generatedUrl);
        return generatedUrl;

    }
}
