package com.rentalcars.controllers;

import com.rentalcars.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Sergey on 02-Jun-17.
 */
@RestController
public class VehicleController {

    @Autowired
    VehicleService service;

    @GetMapping(value = "getCarsByPrice")
    public List<String> getCarsByPrice() {
        return service.printCarsByPriceASC();
    }

    @GetMapping(value = "getCarSpecification")
    public List<String> getCarsSpecification() {
        return service.printCalculatedSpecification();
    }

    @GetMapping(value = "getBestSupplierPerCar")
    public List<String> getBestSupplierPerCar() {
        return service.printHighestRatedSupplierDESC();
    }

    @GetMapping(value = "getCarPerScore")
    public List<String> getCarPerScore() {
        return service.printVehiclesByBrakeDownsDESC();
    }
}
