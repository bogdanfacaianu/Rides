package com.bookings.rides.entity;

import java.io.IOException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Component;

@Component
public class ApiClient {

    public String getResponse(String url) {
        try {
            HttpClient client = new HttpClient();
            HttpMethod method = new GetMethod(url);
            client.executeMethod(method);
            String response = new String(method.getResponseBody());
            return response;
        } catch (IOException e) {
            return e.getMessage();
        }

    }
}
