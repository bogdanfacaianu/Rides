package com.bookings.rides.entity.response;

import com.bookings.rides.entity.Car;
import java.util.List;

public class SupplierResponse {

    private String supplier_id;
    private String pickup;
    private String  dropoff;
    private List<Car> options;

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDropoff() {
        return dropoff;
    }

    public void setDropff(String dropoff) {
        this.dropoff = dropoff;
    }

    public List<Car> getOptions() {
        return options;
    }

    public void setOptions(List<Car> options) {
        this.options = options;
    }
}
