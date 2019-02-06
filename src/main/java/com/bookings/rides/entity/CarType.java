package com.bookings.rides.entity;

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
}
