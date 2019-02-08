package com.bookings.rides;

import com.bookings.rides.entity.Car;
import com.bookings.rides.entity.CarType;
import com.bookings.rides.service.SupplierService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class RidesConsoleApplication {

    private static final int DEFAULT_MAXIMUM_PASSENGERS = CarType.MINIBUS.getSeats();

    private static SupplierService service;

    @Autowired
    private SupplierService supplierService;

    @PostConstruct
    public void setSupplierService() {
        service = this.supplierService;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(RidesApplication.class).run();
        RidesConsoleApplication app = new RidesConsoleApplication();

        String latitude = args[0];
        String longitude = args[1];
        int maximumPassengers = args.length == 3 ? Integer.parseInt(args[2]) : DEFAULT_MAXIMUM_PASSENGERS;

        List<Car> response = service.getSupplierResponses(latitude, longitude, maximumPassengers);
        String offers = service.convertToJson(response);

        System.out.println(offers);
    }
}
