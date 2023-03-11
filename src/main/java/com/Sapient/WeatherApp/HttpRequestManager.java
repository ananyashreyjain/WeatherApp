package com.Sapient.WeatherApp;
import org.springframework.web.client.RestTemplate;

import com.Sapient.WeatherApp.models.Request;
import com.Sapient.WeatherApp.utilites.JsonParser;
import com.Sapient.WeatherApp.models.RawResponse;
import com.Sapient.WeatherApp.utilites.ResponseFormatter;
import com.Sapient.WeatherApp.models.Response;
import com.Sapient.WeatherApp.data.Cache;

public class HttpRequestManager {
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
        final Response response = ResponseFormatter.doFormatting(rawResponse);
        return response.toString();
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