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

    private final Request request;
    private static final String HTTP_REQUEST_ERROR 
            = "Request couldn't be completed due to internal error\n";
    private static final String OFFLINE_MODE_ERROR 
            = "Request couldn't be completed in offline mode\n";

    public HttpRequestManager(final Request request){
        this.request = request;
    }

    public String makeRequest() {
  
        RawResponse rawResponse = retrieveFromCache();
        if(rawResponse == null){
            if(isToggleOnline()){
                rawResponse = makeRequestAndCache();
                if(rawResponse == null){
                    return HTTP_REQUEST_ERROR;
                }
                return ResponseFormatter.doFormatting(rawResponse).toString();
            }
            return OFFLINE_MODE_ERROR;
        }
        return ResponseFormatter.doFormatting(rawResponse).toString();
    }

    private String generateUrl(){
        final String generatedUrl = request.URL + "?" 
        + "appid" + "=" + request.appid
        + "&" + "q" + "=" + request.location 
        + "&" + "cnt" + "=" + request.cnt
        + "&" + "units=metric";
        return generatedUrl;
    }

    private RawResponse retrieveFromCache(){
        RawResponse rawResponse = Cache.getCache().getHistory(request);
        if(rawResponse == null){
            Logger.getLogger().logInfo("Location " + request.location + " not found in cache");
            return null;
        } 
        Logger.getLogger().logInfo("Location " + request.location + " found in cache");
        rawResponse.cached();
        return rawResponse;
    }

    private Boolean isToggleOnline(){
        if(!request.onlineMode){
            Logger.getLogger().logWarn("Required request for location "
             + request.location + " made by " + request.userName 
             + " couldn't be completed without a network call");
            return false;
        }
        return true;
    }

    private RawResponse makeRequestAndCache(){
        try{
            final String url = generateUrl();
            final RestTemplate restTemplate = new RestTemplate();
            final String response = restTemplate.getForObject(url, String.class);
            Logger.getLogger().logInfo("Request was made with url => " + url);
            RawResponse rawResponse = JsonParser.parse(response);
            Cache.getCache().storeHistory(request, rawResponse);
            Logger.getLogger().logInfo("Location " + request.location + " got cached");
            return rawResponse;

        } catch(Exception e){
            Logger.getLogger().logError(e.toString());
            return null;
        }
    }
}