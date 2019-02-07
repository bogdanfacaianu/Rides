package com.bookings.rides.api;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import com.bookings.rides.RidesTestHelper;
import com.bookings.rides.entity.api.ApiRestOperations;
import com.bookings.rides.entity.response.SupplierResponse;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class ApiRestOperationsTest extends RidesTestHelper {

    private static final String API_URL = "https://techtest.rideways.com/dave";
    private String url = API_URL + "?" + "pickup=" + PICKUP + "&" + "dropoff=" + DROPOFF;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ApiRestOperations api;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        api = new ApiRestOperations(restTemplate);
    }

    @Test
    public void testApiResponse() {
        SupplierResponse supplierResponse = createSupplierResponse(DAVE, new ArrayList<>());

        when(restTemplate.getForObject(url, SupplierResponse.class)).thenReturn(supplierResponse);
        Optional<SupplierResponse> response = api.getResponse(url);

        assertThat(response).isNotEqualTo(Optional.empty());
        assertThat(response.get()).isEqualTo(supplierResponse);
    }

    @Test
    public void testApiResponse_returnsNull() {
        when(restTemplate.getForObject(url, SupplierResponse.class)).thenReturn(null);
        Optional<SupplierResponse> response = api.getResponse(url);

        assertThat(response).isEqualTo(Optional.empty());
    }

    @Test
    public void testApiResponse_throwsException() {
        when(restTemplate.getForObject(url, SupplierResponse.class)).thenThrow(RestClientException.class);

        Optional<SupplierResponse> response = api.getResponse(url);

        assertThat(response).isEqualTo(Optional.empty());
    }
}
