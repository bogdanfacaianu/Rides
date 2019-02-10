package com.bookings.rides;

import com.bookings.rides.entity.Car;
import com.bookings.rides.entity.CarType;
import com.bookings.rides.entity.api.Api;
import com.bookings.rides.entity.response.SupplierResponse;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Qualifier;

public class RidesTestHelper {

    public static final String PICKUP = "51.00000,1.0000";
    public static final String DROPOFF = "51.00000,1.0000";
    public static final String DAVE = "DAVE";
    public static final String ERIC = "ERIC";
    public static final String JEFF = "JEFF";
    public static final int DEFAULT_MAXIMUM_PASSENGERS = 16;
    public static final int MAXIMUM_PASSENGERS = 6;
    public static final int UNDERVALUED_MAXIMUM_PASSENGERS = 0;

    @Mock
    @Qualifier("dave")
    protected Api daveApi;

    @Mock
    @Qualifier("eric")
    protected Api ericApi;

    @Mock
    @Qualifier("jeff")
    protected Api jeffApi;

    public SupplierResponse createSupplierResponse(String supplierName, List<Car> options) {
        SupplierResponse supplierResponse = new SupplierResponse();
        supplierResponse.setDropff(DROPOFF);
        supplierResponse.setPickup(PICKUP);
        supplierResponse.setSupplier_id(supplierName);
        supplierResponse.setOptions(options);
        return supplierResponse;
    }

    public List<Car> createCarOptionsListForAGivenSupplier(String supplierName, CarType carType, int numberOfOptions, double price) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < numberOfOptions; i++) {
            Car car = new Car();
            car.setSupplierName(supplierName);
            car.setCar_type(carType);
            car.setPrice(price);
            cars.add(car);
        }
        return cars;
    }
}
