package com.Sapient.WeatherApp.models;

import java.time.LocalDateTime;

public class HeapNode {
    public final LocalDateTime timeStamp;
    public final String location;

    public HeapNode(LocalDateTime timeStamp, String location){
        this.timeStamp = timeStamp;
        this.location = location;
    }
}
