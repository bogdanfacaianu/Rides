package com.bookings.rides;

import com.bookings.rides.entity.Car;
import com.bookings.rides.entity.CarType;
import com.bookings.rides.service.SupplierService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RidesApplication implements CommandLineRunner {

    @Autowired
    private SupplierService supplierService;

    private static final int DEFAULT_MAXIMUM_PASSENGERS = CarType.MINIBUS.getSeats();

    public static void main(String[] args) {
        SpringApplication.run(RidesApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (args.length > 0) {
            String latitude = args[0];
            String longitude = args[1];
            int maximumPassengers = args.length == 3 ? Integer.parseInt(args[2]) : DEFAULT_MAXIMUM_PASSENGERS;

            List<Car> response = supplierService.getSupplierResponses(latitude, longitude, maximumPassengers);
            String offers = supplierService.convertToJson(response);

            System.out.println(offers);

            System.exit(0);
        }
    }

}

