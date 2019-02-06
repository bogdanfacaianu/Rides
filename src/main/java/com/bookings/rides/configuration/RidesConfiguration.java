package com.bookings.rides.configuration;

import com.google.gson.GsonBuilder;
import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RidesConfiguration {

    @Bean
    public GsonBuilder gsonBuilder() {
        return new GsonBuilder();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(2L)).build();
    }
}
