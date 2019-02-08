package com.bookings.rides.configuration;

import com.bookings.rides.entity.api.Api;
import com.bookings.rides.entity.api.ApiFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {

    @Autowired
    private ApiFactory apiFactory;

    @Bean
    @Qualifier("dave")
    public Api getDaveApi() {
        return apiFactory.getApi("https://techtest.rideways.com/dave", "dave");
    }

    @Bean
    @Qualifier("eric")
    public Api getEricApi() {
        return apiFactory.getApi("https://techtest.rideways.com/eric", "eric");
    }

    @Bean
    @Qualifier("jeff")
    public Api getJeffApi() {
        return apiFactory.getApi("https://techtest.rideways.com/jeff", "jeff");
    }
}
