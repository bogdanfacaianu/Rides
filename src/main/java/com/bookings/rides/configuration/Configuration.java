package com.bookings.rides.configuration;

import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public GsonBuilder gsonBuilder() {
        return new GsonBuilder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
