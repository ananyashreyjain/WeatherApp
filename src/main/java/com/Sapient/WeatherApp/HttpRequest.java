package com.Sapient.WeatherApp;
import org.springframework.web.client.RestTemplate;

import com.Sapient.WeatherApp.models.Request;
import com.Sapient.WeatherApp.UrlGenerator;
import com.Sapient.WeatherApp.JsonParser;
import com.Sapient.WeatherApp.models.RawResponse;
import com.Sapient.WeatherApp.data.Cache;

public class HttpRequest {
    public static String makeRequest(Request request) {
        RestTemplate restTemplate = new RestTemplate();
        RawResponse rawResponse = Cache.getCache().getHistory(request);
        if(rawResponse == null){
            String response = restTemplate.getForObject(generateUrl(request), String.class);
            rawResponse = JsonParser.parse(response);
            Cache.getCache().storeHistory(request, rawResponse);
        } else {
            rawResponse.cached();
        }
        String ret = "High Temperatures - " + rawResponse.maxTemp.toString();
        ret += "\n<br>Low Temperatures - " + rawResponse.minTemp.toString();
        ret += "\n<br>Wind Speeds - " + rawResponse.windSpeed.toString();
        ret += "\n<br>Weather - " + rawResponse.weather.toString();
        ret += "\n<br>network Call - " + (!rawResponse.isCached());
        return ret;
    }

    private static String generateUrl(final Request request){

        final String generatedUrl = request.URL + "?" 
        + "appid" + "=" + request.appid
        + "&" + "q" + "=" + request.location 
        + "&" + "cnt" + "=" + request.cnt;

        System.out.println(generatedUrl);
        return generatedUrl;

    }
}