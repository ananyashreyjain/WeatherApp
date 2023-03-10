package com.Sapient.WeatherApp;
import org.springframework.web.client.RestTemplate;

import com.Sapient.WeatherApp.models.Request;
import com.Sapient.WeatherApp.UrlGenerator;
import com.Sapient.WeatherApp.JsonParser;
import com.Sapient.WeatherApp.models.RawResponse;

public class HttpRequest {
    public static String makeRequest(Request request) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(UrlGenerator.generateUrl(request), String.class);
        RawResponse rawResponse = JsonParser.parse(response);
        String ret = "High Temperatures - " + rawResponse.maxTemp.toString();
        ret += "\n<br>Low Temperatures - " + rawResponse.minTemp.toString();
        ret += "\n<br>Wind Speeds - " + rawResponse.windSpeed.toString();
        ret += "\n<br>Weather - " + rawResponse.weather.toString();
        return ret;
    }
}