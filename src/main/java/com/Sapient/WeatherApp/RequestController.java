package com.Sapient.WeatherApp;  
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.Sapient.WeatherApp.utilites.Logger;
import com.Sapient.WeatherApp.HttpRequestManager;
import com.Sapient.WeatherApp.models.Request;

import java.util.Map;

/**
 * Class responsible for listening to requests
 * and intiate processing of the same
 */
@RestController  
public class RequestController
{
    public static String NAME = "name";
    public static String LOCATION = "location";
    public static String OFFLINE = "offline";
    public static String ERROR = String.format(
        "Expected params %s and %s are missing", NAME, LOCATION
    );

    /**
     * Function that describes the procedure for handling a request
     */
    @GetMapping("/")  
    public String request(@RequestParam Map<String,String> params){
        if(params.containsKey(NAME) && params.containsKey(LOCATION)){
            final Boolean onlineMode = !params.containsKey(OFFLINE);
            Logger.getLogger().logInfo("Received request from " 
                + params.get(NAME) + " for location " + params.get(LOCATION)
                + " with onlineMode set as " + onlineMode.toString());
            
            return new HttpRequestManager(
                new Request(params.get(NAME), params.get(LOCATION), onlineMode)
            ).makeRequest();
        } else {
            Logger.getLogger().logError("Received request has missing params");
            return ERROR;
        }
    }
}  