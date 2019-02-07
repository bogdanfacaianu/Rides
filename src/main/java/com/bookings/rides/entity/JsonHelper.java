package com.bookings.rides.entity;

import com.google.gson.Gson;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonHelper {

    @Autowired
    private Gson gson;

    public String convertToJsonOutput(List<Car> outputList) {
        return gson.toJson(outputList);
    }
}
