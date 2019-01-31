package com.bookings.rides.controller;

import com.bookings.rides.entity.Api;
import com.bookings.rides.entity.ApiClient;
import com.bookings.rides.entity.DaveApi;
import com.bookings.rides.entity.EricApi;
import com.bookings.rides.entity.JeffApi;
import com.bookings.rides.entity.SupplierResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class RestController {

    private final ApiClient client;
    private final Gson gson;

    @Autowired
    public RestController(ApiClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    @GetMapping(value = "/getDave", produces = "application/json")
    public String getDaveResponse() {
        Api dave = new DaveApi(client, gson);
        SupplierResponse response = dave.get("51.470020,-0.454295", "51.00000,1.0000");
        return gson.toJson(response, SupplierResponse.class);
    }

    @GetMapping(value = "/getEric", produces = "application/json")
    public String getEricResponse() {
        Api eric = new EricApi(client, gson);
        SupplierResponse response = eric.get("51.470020,-0.454295", "51.00000,1.0000");
        return gson.toJson(response, SupplierResponse.class);
    }

    @GetMapping(value = "/getJeff", produces = "application/json")
    public String getJeffResponse() {
        Api jeff = new JeffApi(client, gson);
        SupplierResponse response = jeff.get("51.470020,-0.454295", "51.00000,1.0000");
        response.sortOptionsByPriceDescending();
        return gson.toJson(response, SupplierResponse.class);
    }
}
