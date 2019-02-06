package com.bookings.rides.entity.api;

import com.bookings.rides.entity.response.SupplierResponse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

@Component
public class ApiRestOperations {

    private final RestOperations restOperations;

    @Autowired
    public ApiRestOperations(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    public Optional<SupplierResponse> getResponse(String url) {
        try {
                SupplierResponse response = restOperations.getForObject(url, SupplierResponse.class);
                return Optional.ofNullable(response);

        } catch (RestClientException ex) {
            return Optional.empty();
        }
    }
}
