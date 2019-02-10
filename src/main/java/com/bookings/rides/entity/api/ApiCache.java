package com.bookings.rides.entity.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiCache {

    private Map<String, Api> cache = new HashMap<>();

    @Autowired
    private List<Api> apis;

    public Api getApi(String api) {
        if(cache == null || cache.isEmpty()) {
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
