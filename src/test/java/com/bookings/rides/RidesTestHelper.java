package com.bookings.rides;

import com.bookings.rides.entity.Car;
import com.bookings.rides.entity.CarType;
import com.bookings.rides.entity.response.SupplierResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RidesTestHelper {

    public static final String PICKUP = "51.00000,1.0000";
    public static final String DROPOFF = "51.00000,1.0000";
    public static final String DAVE = "DAVE";
    public static final String ERIC = "ERIC";
    public static final String JEFF = "JEFF";
    public static final int DEFAULT_MAXIMUM_PASSENGERS = 16;
    public static final int MAXIMUM_PASSENGERS = 4;
    public static final boolean PRICE_DESCENDING = true;

    public SupplierResponse createSupplierResponse(String supplierName) {
        SupplierResponse supplierResponse = new SupplierResponse();
        supplierResponse.setDropff(DROPOFF);
        supplierResponse.setPickup(PICKUP);
        supplierResponse.setSupplier_id(supplierName);
        supplierResponse.setOptions(
            createCarOptionsListForAGivenSupplier(supplierName, 2));
        return supplierResponse;
    }

    public List<Car> createCarOptionsListForAGivenSupplier(String supplierName, int numberOfOptions) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < numberOfOptions; i++) {
            Car car = new Car();
            car.setSupplierName(supplierName);
            car.setCar_type(CarType.STANDARD);
            car.setPrice(new Random().nextDouble());
            cars.add(car);
        }
        return cars;
    }
}
