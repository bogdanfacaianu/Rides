package com.bookings.rides.controller;

import com.bookings.rides.entity.Api;
import com.bookings.rides.entity.ApiRestOperations;
import com.bookings.rides.entity.Car;
import com.bookings.rides.entity.CarType;
import com.bookings.rides.entity.DaveApi;
import com.bookings.rides.entity.EricApi;
import com.bookings.rides.entity.JeffApi;
import com.bookings.rides.entity.ResultResponse;
import com.bookings.rides.entity.SupplierResponse;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class RestController {

    private final ApiRestOperations client;
    private final Gson gson;

    @Autowired
    public RestController(ApiRestOperations client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    @GetMapping(value = "/getDave/{maximumPassengers}", produces = "application/json")
    public String getDaveResponse(@PathVariable("maximumPassengers") int maximumPassengers) {
        Api dave = new DaveApi(client, gson);
        SupplierResponse response = dave.get("51.470020,-0.454295", "51.00000,1.0000");

        response.filterCarOptionsByNumberOfPassengers(maximumPassengers);
        response.sortOptionsByPriceDescending();

        return gson.toJson(response, SupplierResponse.class);
    }

    @GetMapping(value = "/getEric/{maximumPassengers}", produces = "application/json")
    public String getEricResponse(@PathVariable("maximumPassengers") int maximumPassengers) {
        Api eric = new EricApi(client, gson);
        SupplierResponse response = eric.get("51.470020,-0.454295", "51.00000,1.0000");

        response.filterCarOptionsByNumberOfPassengers(maximumPassengers);
        response.sortOptionsByPriceDescending();

        return gson.toJson(response, SupplierResponse.class);
    }

    @GetMapping(value = "/getJeff/{maximumPassengers}", produces = "application/json")
    public String getJeffResponse(@PathVariable("maximumPassengers") int maximumPassengers) {
        Api jeff = new JeffApi(client, gson);
        SupplierResponse response = jeff.get("51.470020,-0.454295", "51.00000,1.0000");

        response.filterCarOptionsByNumberOfPassengers(maximumPassengers);
        response.sortOptionsByPriceDescending();

        return gson.toJson(response, SupplierResponse.class);
    }

    @GetMapping(value = "/getCarOptions/{maximumPassengers}", produces = "application/json")
    public String getCarOptionsFromAllSuppliers(@PathVariable("maximumPassengers") int maximumPassengers) {
        Api dave = new DaveApi(client, gson);
        SupplierResponse daveResponse = dave.get("51.470020,-0.454295", "51.00000,1.0000");

        daveResponse.filterCarOptionsByNumberOfPassengers(maximumPassengers);
        daveResponse.sortOptionsByPriceDescending();

        Api eric = new EricApi(client, gson);
        SupplierResponse ericResponse = eric.get("51.470020,-0.454295", "51.00000,1.0000");

        ericResponse.filterCarOptionsByNumberOfPassengers(maximumPassengers);
        ericResponse.sortOptionsByPriceDescending();

        Api jeff = new JeffApi(client, gson);
        SupplierResponse jeffResponse = jeff.get("51.470020,-0.454295", "51.00000,1.0000");

        jeffResponse.filterCarOptionsByNumberOfPassengers(maximumPassengers);
        jeffResponse.sortOptionsByPriceDescending();

        Map<String, ResultResponse> results = new HashMap<>();

        Arrays.stream(CarType.values()).forEach(carType -> results.put(carType.name(), new ResultResponse(carType.name())));

        updateResultPrices(daveResponse, results);
        updateResultPrices(ericResponse, results);
        updateResultPrices(jeffResponse, results);

        List<ResultResponse> filteredResults = results.values().stream()
            .filter(resultResponse -> Objects.nonNull(resultResponse.getSupplier()))
            .collect(Collectors.toList());

        filteredResults.sort(Comparator.comparingDouble(ResultResponse::getPrice));

        return gson.toJson(filteredResults);
    }

    private void updateResultPrices(SupplierResponse supplierResponse, Map<String, ResultResponse> results) {
        for (Car car : supplierResponse.getOptions()) {
            ResultResponse resultResponse = results.get(car.getCar_type());
            double currentPrice = resultResponse.getPrice();
            if (currentPrice == 0 || resultResponse.getPrice() > car.getPrice()) {
                resultResponse.setPrice(car.getPrice());
                resultResponse.setSupplier(supplierResponse.getSupplier_id());
                results.replace(car.getCar_type(), resultResponse);
            }
        }
    }

}
