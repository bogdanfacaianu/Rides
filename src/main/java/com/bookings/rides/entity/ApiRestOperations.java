package com.bookings.rides.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

@Component
public class ApiRestOperations {

    private final RestOperations restOperations;

    @Autowired
    public ApiRestOperations(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    public String getResponse(String url) {
            ResponseEntity<String> response = restOperations.getForEntity(url, String.class);
            return response.getBody();
    }
}
