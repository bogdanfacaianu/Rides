package com.bookings.rides;

import static com.google.common.truth.Truth.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.bookings.rides.controller.RidesController;
import com.bookings.rides.entity.JsonHelper;
import com.bookings.rides.service.SupplierService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RidesApplication.class})
public class RidesControllerIntegrationTest {

    public static final String PICKUP = "51.00000,1.0000";
    public static final String DROPOFF = "51.00000,1.0000";
    public static final String DAVE = "DAVE";
    public static final String ERIC = "ERIC";
    public static final String JEFF = "JEFF";
    public static final int DEFAULT_MAXIMUM_PASSENGERS = 16;
    public static final int MAXIMUM_PASSENGERS = 4;
    public static final boolean PRICE_DESCENDING = true;

    private MockMvc mockMvc;
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private JsonHelper jsonHelper;

    private RidesController ridesController;

    @Before
    public void setup() {
        ridesController = new RidesController();
        mockMvc = standaloneSetup(ridesController).build();
    }


    @Test
    public void testGetOffersFromDave() throws Exception {
        MvcResult result = mockMvc.perform(get("/getOptionsFromDave")
            .header("Accept", MediaType.APPLICATION_JSON)
            .header("Content-Type", "application/json")
            .param("pickup", PICKUP)
            .param("dropoff", DROPOFF))
            .andExpect(status().isOk()).andReturn();

        assertThat(result).isNotNull();
    }



}
