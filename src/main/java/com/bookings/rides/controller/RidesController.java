package com.bookings.rides.controller;

import com.bookings.rides.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RidesController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping(value = "/getCarOptions", produces = "application/json")
    public String getOffers(@RequestParam(value = "pickup") String pickup,
                            @RequestParam(value = "dropoff") String dropoff,
                            @RequestParam(value = "maximumPassengers", required = false, defaultValue = "16") int maximumPassengers) {
        String result = supplierService.getSupplierResponses(pickup, dropoff, maximumPassengers);
        return result;
    }

    @GetMapping(value = "/getOptionsFromDave", produces = "application/json")
    public String getOffersFromDave(@RequestParam(value = "pickup") String pickup,
                                    @RequestParam(value = "dropoff") String dropoff,
                                    @RequestParam(value = "priceDescending", required = false, defaultValue = "false") boolean priceDescending) {
        return supplierService.getDaveResponse(pickup, dropoff, priceDescending);
    }
}
