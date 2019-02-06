package com.bookings.rides.entity;

public class Car {

    private CarType car_type;
    private double price;

    public String getCar_type() {
        return car_type.name();
    }

    public CarType getCarTypeObj() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = CarType.valueOf(car_type);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}