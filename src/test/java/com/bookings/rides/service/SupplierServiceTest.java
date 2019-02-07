package com.bookings.rides.service;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.bookings.rides.RidesTestHelper;
import com.bookings.rides.entity.Car;
import com.bookings.rides.entity.CarType;
import com.bookings.rides.entity.JsonHelper;
import com.bookings.rides.entity.api.Api;
import com.bookings.rides.entity.response.SupplierResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Qualifier;

public class SupplierServiceTest extends RidesTestHelper {

    private static final String JSON_OUTPUT = "json-converted-output";

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

    @Spy
    private List<Api> supplierApis = new ArrayList<>();

    @InjectMocks
    private SupplierService supplierService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDaveResponse() {
        SupplierResponse supplierResponse = mockDaveApiCall(CarType.STANDARD);

        List<Car> response = supplierService.getDaveResponse(PICKUP, DROPOFF, false);

        assertThat(response).isNotEmpty();
        assertThat(response.size()).isEqualTo(supplierResponse.getOptions().size());
    }

    @Test
    public void testAllSuppliersResponse() {
        mockApiCalls();

        List<Car> response = supplierService.getSupplierResponses(PICKUP, DROPOFF, DEFAULT_MAXIMUM_PASSENGERS);
        assertThat(response.size()).isEqualTo(2);
        Car option1 = response.get(0);
        assertThat(option1.getSupplierName()).isEqualTo(DAVE);
        assertThat(option1.getPrice()).isNonZero();
        assertThat(option1.getCar_type()).isEqualTo(CarType.STANDARD);

        Car option2 = response.get(1);
        assertThat(option2.getSupplierName()).isEqualTo(ERIC);
        assertThat(option2.getPrice()).isNonZero();
        assertThat(option2.getCar_type()).isEqualTo(CarType.EXECUTIVE);
    }

    @Test
    public void testAllSuppliersResponse_withLimitedNumberOfPassengers() {
        mockDaveApiCall(CarType.STANDARD);
        mockEricApiCall(CarType.PEOPLE_CARRIER);
        mockJeffApiCall(CarType.MINIBUS);

        addAllApiCalls();

        List<Car> response = supplierService.getSupplierResponses(PICKUP, DROPOFF, MAXIMUM_PASSENGERS);
        assertThat(response.size()).isEqualTo(2);
        Car option1 = response.get(0);
        assertThat(option1.getSupplierName()).isEqualTo(DAVE);
        assertThat(option1.getPrice()).isNonZero();
        assertThat(option1.getCar_type()).isEqualTo(CarType.STANDARD);

        Car option2 = response.get(1);
        assertThat(option2.getSupplierName()).isEqualTo(ERIC);
        assertThat(option2.getPrice()).isNonZero();
        assertThat(option2.getCar_type()).isEqualTo(CarType.PEOPLE_CARRIER);
    }

    @Test
    public void testJsonOutput() {
        when(jsonHelper.convertToJsonOutput(any())).thenReturn(JSON_OUTPUT);

        assertThat(supplierService.convertToJson(new ArrayList<>())).isEqualTo(JSON_OUTPUT);
    }

    private SupplierResponse mockDaveApiCall(CarType carType) {
        List<Car> cars = createCarOptionsListForAGivenSupplier(DAVE, carType, 2, 232425);
        SupplierResponse daveResponse = createSupplierResponse(DAVE, cars);
        when(daveApi.get(any(), any())).thenReturn(java.util.Optional.ofNullable(daveResponse));
        return daveResponse;
    }

    private SupplierResponse mockEricApiCall(CarType carType) {
        List<Car> cars = createCarOptionsListForAGivenSupplier(ERIC, carType, 2, 45218);
        SupplierResponse ericResponse = createSupplierResponse(ERIC, cars);
        when(ericApi.get(any(), any())).thenReturn(java.util.Optional.ofNullable(ericResponse));
        return ericResponse;
    }

    private SupplierResponse mockJeffApiCall(CarType carType) {
        List<Car> cars = createCarOptionsListForAGivenSupplier(JEFF, carType, 2, 8787871);
        SupplierResponse jeffResponse = createSupplierResponse(JEFF, cars);
        when(jeffApi.get(any(), any())).thenReturn(java.util.Optional.ofNullable(jeffResponse));
        return jeffResponse;
    }

    private void mockApiCalls() {
        mockDaveApiCall(CarType.STANDARD);
        mockEricApiCall(CarType.EXECUTIVE);
        mockJeffApiCall(CarType.STANDARD);

        addAllApiCalls();
    }

    private void addAllApiCalls() {
        supplierApis.add(daveApi);
        supplierApis.add(ericApi);
        supplierApis.add(jeffApi);
    }

}
