package com.bookings.rides.entity.api;

import org.springframework.stereotype.Component;

@Component
public class ApiFactory {

    private final ApiRestOperations apiRestOperations;

    public ApiFactory(ApiRestOperations apiRestOperations) {
        this.apiRestOperations = apiRestOperations;
    }

    public Api getApi(String url) {
        return new Api(url, apiRestOperations);
    }
}
