package com.bookings.rides.service;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.bookings.rides.RidesTestHelper;
import com.bookings.rides.entity.JsonHelper;
import com.bookings.rides.entity.api.Api;
import com.bookings.rides.entity.response.SupplierResponse;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;

public class SupplierServiceTest extends RidesTestHelper {

    @Mock
    private JsonHelper jsonHelper;

    @Mock
    @Qualifier("dave")
    private Api daveApi;

    @Mock
    @Qualifier("eric")
    private Api ericApi;

    @Mock
    @Qualifier("jeff")
    private Api jeffApi;

    @InjectMocks
    private SupplierService supplierService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDaveResponse() {
        SupplierResponse supplierResponse = createSupplierResponse(DAVE);
        when(daveApi.get(any(), any())).thenReturn(java.util.Optional.ofNullable(supplierResponse));
        when(jsonHelper.convertToJsonOutput(any())).thenReturn("response");

        String respone = supplierService.getDaveResponse(PICKUP, DROPOFF, false);

        assertThat(respone).isEqualTo("response");
    }

    @Test
    public void testAllSuppliersResponse() {
        SupplierResponse daveResponse = createSupplierResponse(DAVE);
        when(daveApi.get(any(), any())).thenReturn(java.util.Optional.ofNullable(daveResponse));


        SupplierResponse ericResponse = createSupplierResponse(ERIC);
        when(daveApi.get(any(), any())).thenReturn(java.util.Optional.ofNullable(ericResponse));


        SupplierResponse jeffResponse = createSupplierResponse(JEFF);
        when(daveApi.get(any(), any())).thenReturn(java.util.Optional.ofNullable(jeffResponse));


        when(jsonHelper.convertToJsonOutput(any())).thenReturn("response");

        String respone = supplierService.getSupplierResponses(PICKUP, DROPOFF, DEFAULT_MAXIMUM_PASSENGERS);

        assertThat(respone).isEqualTo("response");
    }

}
