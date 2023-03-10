package com.Sapient.WeatherApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class WeatherAppApplication {

	public static Integer PORT = 8080;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WeatherAppApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", PORT));
        app.run(args);
	}
}
