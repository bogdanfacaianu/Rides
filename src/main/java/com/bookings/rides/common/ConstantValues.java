package com.bookings.rides.common;

import com.bookings.rides.entity.CarType;

public class ConstantValues {

    public static final int DEFAULT_MAXIMUM_PASSENGERS = CarType.MINIBUS.getSeats();
    public static final int MINIMUM_ALLOWED_MAXIMUM_PASSENGERS = CarType.STANDARD.getSeats();

    public static final String DAVE = "dave";

}
