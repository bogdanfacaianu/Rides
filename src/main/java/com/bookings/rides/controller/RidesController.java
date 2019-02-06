package com.bookings.rides.controller;

import com.bookings.rides.entity.Car;
import com.bookings.rides.service.SupplierService;
import com.google.gson.Gson;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RidesController {

    private final SupplierService supplierService;
    private final Gson gson;

    @Autowired
    public RidesController(SupplierService supplierService, Gson gson) {
        this.supplierService = supplierService;
        this.gson = gson;
    }

    @GetMapping(value = "/getCarOptions", produces = "application/json")
    public String getOffers(@RequestParam(value = "pickup") String pickup,
                            @RequestParam(value = "dropoff") String dropoff,
                            @RequestParam(value = "maximumPassengers", required = false, defaultValue = "4") int maximumPassengers) {
        Collection<Car> responses = supplierService.getSupplierResponses(pickup, dropoff, maximumPassengers);
        return gson.toJson(responses);
    }

    @GetMapping(value = "/getOptionsFromDave", produces = "application/json")
    public String getOffers(@RequestParam(value = "pickup") String pickup,
                            @RequestParam(value = "dropoff") String dropoff,
                            @RequestParam(value = "priceDescending", required = false, defaultValue = "false") boolean priceDescending) {
        Collection<Car> responses = supplierService.getDaveResponse(pickup, dropoff, priceDescending);
        return gson.toJson(responses);
    }
}
