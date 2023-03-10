package com.Sapient.WeatherApp;  
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.Sapient.WeatherApp.HttpRequest;
import com.Sapient.WeatherApp.models.Request;

import java.util.Map;

@RestController  
public class RequestController
{
    public static String NAME = "name";
    public static String LOCATION = "location";
    public static String ERROR = String.format(
        "Expected params %s and %s are missing", NAME, LOCATION
    );

    @GetMapping("/")  
    public String request(@RequestParam Map<String,String> params){
        if(params.containsKey(NAME) && params.containsKey(LOCATION)){
            return HttpRequest.makeRequest(
                new Request(params.get(NAME), params.get(LOCATION))
            );
        } else {
            return ERROR;
        }
    }
}  