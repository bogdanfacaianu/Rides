package com.bookings.rides.service;

import com.bookings.rides.entity.Car;
import com.bookings.rides.entity.JsonHelper;
import com.bookings.rides.entity.api.Api;
import com.bookings.rides.entity.response.SupplierResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    @Autowired
    private List<Api> supplierApis;

    @Autowired
    @Qualifier("dave")
    private Api daveApi;

    @Autowired
    private JsonHelper jsonHelper;

    public String getSupplierResponses(String pickup, String dropoff, int maximumPassengers) {
        List<Car> cars = new ArrayList<>();

        supplierApis.forEach(api -> {
            Optional<SupplierResponse> supplierResponse = api.get(pickup, dropoff);
            supplierResponse.ifPresent(response -> {
                response.getOptions().forEach(car -> car.setSupplierName(response.getSupplier_id()));
                cars.addAll(response.getOptions());
            });
        });

        return filterResultsByPassengersAndPrice(cars, maximumPassengers);
    }

    private String filterResultsByPassengersAndPrice(List<Car> cars, int maximumPassengers) {
        cars.removeIf(car -> car.getCar_type().getSeats() > maximumPassengers);
        ArrayList<Car> filteredCars = new ArrayList<>(filterCarResults(cars));
        sortList(filteredCars, true);
        return jsonHelper.convertToJsonOutput(filteredCars);
    }

    public String getDaveResponse(String pickup, String dropoff, boolean priceDescending) {
        List<Car> cars = new ArrayList<>();
        Optional<SupplierResponse> davesResponse = daveApi.get(pickup, dropoff);
        davesResponse.ifPresent(response -> cars.addAll(response.getOptions()));
        if (priceDescending) {
            sortList(cars, priceDescending);
        }
        return jsonHelper.convertToJsonOutput(cars);
    }


    private Collection<Car> filterCarResults(List<Car> cars) {
        Map<String, Car> filteredResults = new HashMap<>();
        sortList(cars, false);
        for (Car car : cars) {
            String carTypeName = car.getCar_type().name();
            if (!filteredResults.containsKey(carTypeName)) {
                filteredResults.put(carTypeName, car);
            }
        }
        return filteredResults.values();
    }

    private void sortList(List<Car> list, boolean descending) {
        if (descending) {
            list.sort(Comparator.comparingDouble(Car::getPrice).reversed());
        } else {
            list.sort(Comparator.comparingDouble(Car::getPrice));
        }
    }

}
