package com.bookings.rides.entity.api;

import com.bookings.rides.entity.response.SupplierResponse;
import java.util.Optional;

public class Api {

    private String url;
    private ApiRestOperations client;
    private String name;

    public Api(String url, ApiRestOperations client, String name) {
        this.url = url;
        this.client = client;
        this.name = name;
    }

    public Optional<SupplierResponse> get(String pickup, String dropoff) {
        return client.getResponse(String.format("%s?pickup=%s&dropoff=%s", url, pickup, dropoff));
    }

    public String getName() {
        return this.name;
    }
}
