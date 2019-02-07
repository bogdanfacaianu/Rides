package com.bookings.rides.controller;

import static com.bookings.rides.RidesTestHelper.DEFAULT_MAXIMUM_PASSENGERS;
import static com.bookings.rides.RidesTestHelper.DROPOFF;
import static com.bookings.rides.RidesTestHelper.MAXIMUM_PASSENGERS;
import static com.bookings.rides.RidesTestHelper.PICKUP;
import static com.bookings.rides.RidesTestHelper.PRICE_DESCENDING;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookings.rides.service.SupplierService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class RidesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SupplierService supplierService;

    @InjectMocks
    private RidesController ridesController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(ridesController).build();
    }

    @Test
    public void testGettingOffersFromDave() throws Exception {
        mockMvc.perform(get("/getOptionsFromDave")
            .param("pickup", PICKUP)
            .param("dropoff", DROPOFF))
            .andExpect(status().isOk());

        verify(supplierService).getDaveResponse(PICKUP, DROPOFF, false);
        verifyNoMoreInteractions(supplierService);
    }

    @Test
    public void testGettingOffersFromDaveWithSortedPrices() throws Exception {
        mockMvc.perform(get("/getOptionsFromDave")
            .param("pickup", PICKUP)
            .param("dropoff", DROPOFF)
            .param("priceDescending", String.valueOf(PRICE_DESCENDING)))
            .andExpect(status().isOk());

        verify(supplierService).getDaveResponse(PICKUP, DROPOFF, PRICE_DESCENDING);
        verifyNoMoreInteractions(supplierService);
    }

    @Test
    public void testGettingOffersFromAllSuppliers() throws Exception {
        mockMvc.perform(get("/getCarOptions")
            .param("pickup", PICKUP)
            .param("dropoff", DROPOFF))
            .andExpect(status().isOk());

        verify(supplierService).getSupplierResponses(PICKUP, DROPOFF, DEFAULT_MAXIMUM_PASSENGERS);
        verifyNoMoreInteractions(supplierService);
    }

    @Test
    public void testGettingOffersFromAllSuppliersWithGivenMaximumPassengers() throws Exception {
        mockMvc.perform(get("/getCarOptions")
            .param("pickup", PICKUP)
            .param("dropoff", DROPOFF)
            .param("maximumPassengers", String.valueOf(MAXIMUM_PASSENGERS)))
            .andExpect(status().isOk());

        verify(supplierService).getSupplierResponses(PICKUP, DROPOFF, MAXIMUM_PASSENGERS);
        verifyNoMoreInteractions(supplierService);
    }
}
