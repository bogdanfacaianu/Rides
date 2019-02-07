package com.bookings.rides;

import com.bookings.rides.entity.Car;
import com.bookings.rides.entity.response.SupplierResponse;
import com.google.gson.Gson;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RidesConsoleApplication {

    private static RestTemplate restTemplate;
    private static Gson gson;

    public static void main(String[] args) {
        restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(2)).build();
        gson = new Gson();

        String latitude = args[0];
        String longitude = args[1];
        String url = "https://techtest.rideways.com/dave" + "?" + "pickup=" + latitude + "&" + "dropoff=" + longitude;

        try {
            SupplierResponse response = restTemplate.getForObject(url, SupplierResponse.class);
            if (response != null) {
                List<Car> carOptions = response.getOptions();
                carOptions.sort(Comparator.comparingDouble(Car::getPrice).reversed());
                System.out.println(gson.toJson(carOptions));
            }
        } catch (RestClientException ex) {
            System.out.println("System communication encountered an error");
        }

    }
}
