package com.bookings.rides.entity.api;

import com.bookings.rides.entity.response.SupplierResponse;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiRestOperations {

    Logger log = LoggerFactory.getLogger(ApiRestOperations.class);

    @Autowired
    private final RestTemplate restTemplate;

    public ApiRestOperations(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<SupplierResponse> getResponse(String url) {
        try {
                SupplierResponse response = restTemplate.getForObject(url, SupplierResponse.class);
                return Optional.ofNullable(response);

        } catch (RestClientException ex) {
            log.error("RestClientException, returning empty object for supplier: {}, with exception: {}", getSupplierNameFromUrl(url), ex.getMessage());
            return Optional.empty();
        }
        catch (Exception ex) {
            log.error("An exception has occured, returning empty object for supplier: {}, with exception: {}", getSupplierNameFromUrl(url), ex.getMessage());
            return Optional.empty();
        }
    }

    private String getSupplierNameFromUrl(String url) {
        return url.substring(30, 34);
    }
}
