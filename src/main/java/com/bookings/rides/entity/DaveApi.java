package com.bookings.rides.entity;

import com.google.gson.Gson;

public class DaveApi extends Api {

    public DaveApi(ApiClient client, Gson gson) {
        super("https://techtest.rideways.com/dave", client, gson);
    }
}
