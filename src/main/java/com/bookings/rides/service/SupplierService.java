package com.bookings.rides.service;

import static com.bookings.rides.common.ConstantValues.MINIMUM_ALLOWED_MAXIMUM_PASSENGERS;

import com.bookings.rides.entity.Car;
import com.bookings.rides.common.JsonHelper;
import com.bookings.rides.entity.CarType;
import com.bookings.rides.entity.api.ApiCache;
import com.bookings.rides.entity.response.SupplierResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    @Autowired
    private ApiCache apiCache;

    @Autowired
    private JsonHelper jsonHelper;

    public List<Car> getSupplierResponses(String pickup, String dropoff, int maximumPassengers) {
        List<Car> cars = new ArrayList<>();

        apiCache.getApis().forEach(api -> {
            Optional<SupplierResponse> supplierResponse = api.get(pickup, dropoff);
            supplierResponse.ifPresent(response -> {
                response.getOptions().forEach(car -> car.setSupplierName(response.getSupplier_id()));
                cars.addAll(response.getOptions());
            });
        });
        return filterResultsByPassengersAndPrice(cars, maximumPassengers);
    }

    private List<Car> filterResultsByPassengersAndPrice(List<Car> cars, int maximumPassengers) {
        final int passengers = maximumPassengers < 1 ? MINIMUM_ALLOWED_MAXIMUM_PASSENGERS : maximumPassengers;
        cars.removeIf(car -> car.getCar_type().getSeats() > passengers);
        ArrayList<Car> filteredCars = new ArrayList<>(filterCarResults(cars));
        sortList(filteredCars, true);
        return filteredCars;
    }

    public List<Car> getSingleApiResponse(String pickup, String dropoff, String apiName) {
        List<Car> cars = new ArrayList<>();
        Optional<SupplierResponse> apiResponse = apiCache.getApi(apiName).get(pickup, dropoff);
        apiResponse.ifPresent(response -> cars.addAll(response.getOptions()));
        sortList(cars, true);
        return cars;
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

    public String convertToJson(List<Car> options) {
        return jsonHelper.convertToJsonOutput(options);
    }

}
