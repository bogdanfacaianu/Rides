package com.bookings.rides.entity;

import com.google.gson.Gson;

public class EricApi extends Api {

    public EricApi(ApiClient client, Gson gson) {
        super("https://techtest.rideways.com/eric", client, gson);
    }
}
