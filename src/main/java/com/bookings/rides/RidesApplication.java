package com.bookings.rides;

import com.bookings.rides.entity.Car;
import com.bookings.rides.entity.CarType;
import com.bookings.rides.service.SupplierService;
import java.util.InputMismatchException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RidesApplication implements CommandLineRunner {

    Logger log = LoggerFactory.getLogger(RidesApplication.class);

    private static final int DEFAULT_MAXIMUM_PASSENGERS = CarType.MINIBUS.getSeats();

    @Autowired
    private SupplierService supplierService;

    public static void main(String[] args) {
        SpringApplication.run(RidesApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String latitude = null, longitude = null;
        int maximumPassengers = DEFAULT_MAXIMUM_PASSENGERS;

        if (args.length > 0) {
            try {
                if (args.length < 2) {
                    log.error("Longitude and Maximum Passengers not provided, exiting application");
                    System.exit(0);
                }

                latitude = args[0];
                longitude = args[1];

                if (args.length < 3) {
                    log.error("Maximum Passengers not provided, leaving with default value of: {}", DEFAULT_MAXIMUM_PASSENGERS);
                } else {
                    maximumPassengers = Integer.parseInt(args[2]);
                }

            } catch (InputMismatchException ex) {
                log.error("Wrong type of argument given, leaving maximum passengers to a default maximum of: {}.", DEFAULT_MAXIMUM_PASSENGERS);
            } catch (NumberFormatException ex) {
                log.error("Number Format Exception cought, leaving maximum passengers to a default maximum of: {}.", DEFAULT_MAXIMUM_PASSENGERS);
            } finally {
                getOffersFromAllSuppliers(latitude, longitude, maximumPassengers);
            }
        }
    }

    private void getOffersFromAllSuppliers(String latitude, String longitude, int maximumPassengers) {
        List<Car> response = supplierService.getSupplierResponses(latitude, longitude, maximumPassengers);
        String offers = supplierService.convertToJson(response);

        System.out.println(offers);

        System.exit(0);
    }

}

