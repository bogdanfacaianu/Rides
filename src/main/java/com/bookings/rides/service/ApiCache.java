package com.bookings.rides.service;

import com.bookings.rides.entity.api.Api;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiCache {
    Map<String, Api> cache;

    @Autowired
    private List<Api> apis;


    public Api getApi(String api) {
        if(cache == null) {
            init();
        }
        return cache.get(api);
    }

    public List<Api> getApis() {
        return apis;
    }

    private void init() {
        for(Api api : apis) {
            cache.put(api.getName(), api);
        }
    }
}
