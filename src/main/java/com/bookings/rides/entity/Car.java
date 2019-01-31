package com.bookings.rides.entity;

public class Car {

    private CarType car_type;
    private double price;
    private int maximumPassengers;

    public String getCar_type() {
        return car_type.name();
    }

    public void setCar_type(String car_type) {
        CarType carType = CarType.valueOf(car_type);
        this.car_type = carType;
        setMaximumPassengers();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMaximumPassengers() {
        return maximumPassengers;
    }

    private void setMaximumPassengers() {
        this.maximumPassengers = CarType.valueOf(car_type.name()).getSeats();
    }
}