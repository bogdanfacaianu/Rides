package com.bookings.rides.entity;

public class ResultResponse {

    private String carType;
    private String supplier;
    private double price;

    public ResultResponse(String carType, String supplier, double price) {
        this.carType = carType;
        this.supplier = supplier;
        this.price = price;
    }

    public ResultResponse(String carType) {
        this.carType = carType;
    }

    public ResultResponse(Car car) {
        this.carType = car.getCar_type();
        this.price = car.getPrice();
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
