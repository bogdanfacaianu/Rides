package com.bookings.rides.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum CarType {
    STANDARD(4),
    EXECUTIVE(4),
    LUXURY(4),
    PEOPLE_CARRIER(6),
    LUXURY_PEOPLE_CARRIER(6),
    MINIBUS(16);

    private final int seats;

    CarType(int seats) {
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    public static List<CarType> getAllTypesWithGivenNumberOfSeats(int seatsRequired) {
        return Arrays.asList(CarType.values()).stream().filter(carType -> carType.seats == seatsRequired).collect(Collectors.toList());
    }

    public static List<CarType> getAllTypesWithMinimumNumberOfSeats(int minimumSeatsRequired) {
        return Arrays.asList(CarType.values()).stream().filter(carType -> carType.seats >= minimumSeatsRequired).collect(Collectors.toList());
    }

    public static Optional<CarType> getCarTypeFromName(String carTypeName) {
        return Arrays.asList(CarType.values()).stream().filter(carType -> carType.name().equals(carTypeName)).findFirst();
    }
}
