package com.bookings.rides.service;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.bookings.rides.RidesTestHelper;
import com.bookings.rides.entity.Car;
import com.bookings.rides.entity.CarType;
import com.bookings.rides.common.JsonHelper;
import com.bookings.rides.entity.api.ApiCache;
import com.bookings.rides.entity.response.SupplierResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SupplierServiceTest extends RidesTestHelper {

    private static final String JSON_OUTPUT = "json-converted-output";

    @Mock
    private JsonHelper jsonHelper;

    @Mock
    private ApiCache apiCache;


    @InjectMocks
    private SupplierService supplierService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDaveResponse() {
        SupplierResponse supplierResponse = mockDaveApiCall(CarType.STANDARD);

        List<Car> response = supplierService.getSingleApiResponse(PICKUP, DROPOFF, DAVE);

        assertThat(response).isNotEmpty();
        assertThat(response.size()).isEqualTo(supplierResponse.getOptions().size());
    }

    @Test
    public void testAllSuppliersResponse() {
        mockDaveApiCall(CarType.STANDARD);
        mockEricApiCall(CarType.EXECUTIVE);
        mockJeffApiCall(CarType.STANDARD);

        when(apiCache.getApis()).thenReturn(Arrays.asList(daveApi, ericApi, jeffApi));

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

        when(apiCache.getApis()).thenReturn(Arrays.asList(daveApi, ericApi, jeffApi));

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
    public void testAllSuppliersResponse_withUnderValuednumberOfPassengers() {
        mockDaveApiCall(CarType.STANDARD);
        mockEricApiCall(CarType.PEOPLE_CARRIER);
        mockJeffApiCall(CarType.MINIBUS);

        when(apiCache.getApis()).thenReturn(Arrays.asList(daveApi, ericApi, jeffApi));

        List<Car> response = supplierService.getSupplierResponses(PICKUP, DROPOFF, UNDERVALUED_MAXIMUM_PASSENGERS);
        assertThat(response.size()).isEqualTo(1);
        Car option1 = response.get(0);
        assertThat(option1.getSupplierName()).isEqualTo(DAVE);
        assertThat(option1.getPrice()).isNonZero();
        assertThat(option1.getCar_type()).isEqualTo(CarType.STANDARD);
    }

    @Test
    public void testJsonOutput() {
        when(jsonHelper.convertToJsonOutput(any())).thenReturn(JSON_OUTPUT);

        assertThat(supplierService.convertToJson(new ArrayList<>())).isEqualTo(JSON_OUTPUT);
    }

    private SupplierResponse mockDaveApiCall(CarType carType) {
        List<Car> cars = createCarOptionsListForAGivenSupplier(DAVE, carType, 2, 232425);
        SupplierResponse daveResponse = createSupplierResponse(DAVE, cars);
        when(apiCache.getApi(DAVE)).thenReturn(daveApi);
        when(daveApi.get(any(), any())).thenReturn(java.util.Optional.ofNullable(daveResponse));
        return daveResponse;
    }

    private SupplierResponse mockEricApiCall(CarType carType) {
        List<Car> cars = createCarOptionsListForAGivenSupplier(ERIC, carType, 2, 45218);
        SupplierResponse ericResponse = createSupplierResponse(ERIC, cars);
        when(apiCache.getApi(ERIC)).thenReturn(ericApi);
        when(ericApi.get(any(), any())).thenReturn(java.util.Optional.ofNullable(ericResponse));
        return ericResponse;
    }

    private SupplierResponse mockJeffApiCall(CarType carType) {
        List<Car> cars = createCarOptionsListForAGivenSupplier(JEFF, carType, 2, 8787871);
        SupplierResponse jeffResponse = createSupplierResponse(JEFF, cars);
        when(apiCache.getApi(JEFF)).thenReturn(jeffApi);
        when(jeffApi.get(any(), any())).thenReturn(java.util.Optional.ofNullable(jeffResponse));
        return jeffResponse;
    }
}
