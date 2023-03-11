package com.Sapient.WeatherApp;
import org.springframework.web.client.RestTemplate;

import com.Sapient.WeatherApp.models.Request;
import com.Sapient.WeatherApp.utilites.JsonParser;
import com.Sapient.WeatherApp.models.RawResponse;
import com.Sapient.WeatherApp.utilites.ResponseFormatter;
import com.Sapient.WeatherApp.models.Response;
import com.Sapient.WeatherApp.data.Cache;
import com.Sapient.WeatherApp.utilites.Logger;

public class HttpRequestManager {
    public static String makeRequest(Request request) {
        RestTemplate restTemplate = new RestTemplate();
        RawResponse rawResponse = Cache.getCache().getHistory(request);
        if(rawResponse == null){
            Logger.getLogger().logInfo("Location " + request.location + " not found in cache");
            if(!request.onlineMode){
                Logger.getLogger().logWarn("Required request for location "
                 + request.location + " made by " + request.userName 
                 + " couldn't be completed without a network call");
                return "Please go online for this request\n";
            }
            final String url = generateUrl(request);
            try{
                final String response = restTemplate.getForObject(url, String.class);
                Logger.getLogger().logInfo("Request was made with url => " + url);
                rawResponse = JsonParser.parse(response);
                Cache.getCache().storeHistory(request, rawResponse);
                Logger.getLogger().logInfo("Location " + request.location + " got cached");

            } catch(Exception e){
                Logger.getLogger().logError(e.toString());
                return "Request could not be completed at the moment\n";
            }
        } else {
            Logger.getLogger().logInfo("Location " + request.location + " found in cache");
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
        return generatedUrl;
    }
}