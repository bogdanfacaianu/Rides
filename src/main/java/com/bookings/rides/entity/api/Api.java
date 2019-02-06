package com.bookings.rides.entity.api;

import com.bookings.rides.entity.response.SupplierResponse;
import java.util.Optional;

public class Api {

    private String url;
    private ApiRestOperations client;

    public Api(String url, ApiRestOperations client) {
        this.url = url;
        this.client = client;
    }

    public Optional<SupplierResponse> get(String pickup, String dropoff) {
        return client.getResponse(String.format("%s?pickup=%s&dropoff=%s", url, pickup, dropoff));
    }
}
