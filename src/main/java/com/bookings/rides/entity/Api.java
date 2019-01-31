package com.bookings.rides.entity;

import com.google.gson.Gson;

public class Api {

    private String url;
    private ApiClient client;
    private Gson gson;

    public Api(String url, ApiClient client, Gson gson) {
        this.url = url;
        this.client = client;
        this.gson = gson;
    }

    public SupplierResponse get(String pickup, String dropoff) {
        String response = client.getResponse(String.format("%s?pickup=%s&dropoff=%s", url, pickup, dropoff));
        return gson.fromJson(response, SupplierResponse.class);
    }
}
