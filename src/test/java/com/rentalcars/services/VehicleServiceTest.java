package com.rentalcars.services;

import com.rentalcars.data.VehicleRepository;
import com.rentalcars.data.dto.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
/**
 * Created by Sergey on 02-Jun-17.
 */
@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {

    @InjectMocks
    VehicleService service;

    @Mock
    VehicleRepository repository;

    @Before
    public void setUp() throws Exception {
        Vehicle v1 = Vehicle.builder().sipp("CDMR").name("Skoda Octavia").price(BigDecimal.valueOf(11.11)).supplier("Sixt").rating(BigDecimal.valueOf(8.6)).build();
        Vehicle v2 = Vehicle.builder().sipp("CWMR").name("Nissan Juke").price(BigDecimal.valueOf(22.22)).supplier("Hertz").rating(BigDecimal.valueOf(8.1)).build();
        Vehicle v3 = Vehicle.builder().sipp("MBMN").name("Vauxhall Corsa").price(BigDecimal.valueOf(33.33)).supplier("Sixt").rating(BigDecimal.valueOf(8.4)).build();
        Vehicle v4 = Vehicle.builder().sipp("CCAR").name("Skoda Octavia").price(BigDecimal.valueOf(44.44)).supplier("Hertz").rating(BigDecimal.valueOf(5.6)).build();
        Vehicle v5 = Vehicle.builder().sipp("SDMR").name("Kia Picanto").price(BigDecimal.valueOf(55.55)).supplier("Hertz").rating(BigDecimal.valueOf(7.9)).build();

        ArrayList<Vehicle> vehicles = new ArrayList<>(Arrays.asList(v1,v2,v3,v4,v5));

        when(repository.getVehicles()).thenReturn(vehicles);
    }


    @Test
    public void shouldReturnListOfCarsOrderedByPrice() {
        List<String> result = service.printCarsByPriceASC();
        List<String> check = new ArrayList<>(Arrays.asList("1.   Skoda Octavia-11.11","2.   Nissan Juke-22.22","3.   Vauxhall Corsa-33.33","4.   Skoda Octavia-44.44","5.   Kia Picanto-55.55"));

        Assert.assertEquals(check, result);

        verify(repository, times(1)).getVehicles();
    }

    @Test
    public void shouldReturnEmptyList() {
        when(repository.getVehicles()).thenReturn(null);
        List<String> result = service.printCarsByPriceASC();

        Assert.assertEquals(new ArrayList<>(), result);

        verify(repository, times(1)).getVehicles();
    }
}