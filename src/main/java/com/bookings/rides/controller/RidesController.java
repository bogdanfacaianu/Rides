package com.bookings.rides.controller;

import static com.bookings.rides.common.ConstantValues.DAVE;

import com.bookings.rides.entity.Car;
import com.bookings.rides.service.SupplierService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RidesController {

    private final SupplierService supplierService;

    @Autowired
    public RidesController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping(value = "/getCarOptions", produces = "application/json")
    public String getOffers(@RequestParam(value = "pickup") String pickup,
                            @RequestParam(value = "dropoff") String dropoff,
                            @RequestParam(value = "maximumPassengers", required = false, defaultValue = "16") int maximumPassengers) {
        List<Car> result = supplierService.getSupplierResponses(pickup, dropoff, maximumPassengers);
        return supplierService.convertToJson(result);
    }

    @GetMapping(value = "/getSupplierCarOptions", produces = "application/json")
    public String getOffersFromSingleApi(@RequestParam(value = "pickup") String pickup,
                                         @RequestParam(value = "dropoff") String dropoff,
                                         @RequestParam(value = "supplier", required = false, defaultValue = DAVE) String apiName) {
        List<Car> result = supplierService.getSingleApiResponse(pickup, dropoff, apiName);
        return supplierService.convertToJson(result);
    }
}