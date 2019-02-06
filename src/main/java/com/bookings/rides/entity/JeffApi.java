package com.bookings.rides.entity;

import com.google.gson.Gson;

public class JeffApi extends Api {

    public JeffApi(ApiRestOperations client, Gson gson) {
        super("https://techtest.rideways.com/jeff", client, gson);
    }

}
