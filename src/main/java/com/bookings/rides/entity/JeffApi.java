package com.bookings.rides.entity;

import com.google.gson.Gson;

public class JeffApi extends Api {

    public JeffApi(ApiClient client, Gson gson) {
        super("https://techtest.rideways.com/jeff", client, gson);
    }

}
