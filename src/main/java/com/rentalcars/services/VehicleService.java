package com.rentalcars.services;

import com.rentalcars.data.VehicleRepository;
import com.rentalcars.data.dto.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 02-Jun-17.
 */
@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;
    private static final String OUTPUT_TEMPLATE = "%-4s %s";

    public List<String> printCarsByPriceASC() {
        List<Vehicle> vehicles = vehicleRepository.getVehicles();
        if (vehicles == null) return new ArrayList<>();

        int[] idx = { 1 };
        return vehicles.stream()
                .sorted(Comparator.comparing(Vehicle::getPrice))
                .map(vehicle -> getOutputLine(idx[0]++, vehicle.getName(), vehicle.getPrice().toString()))
                .collect(Collectors.toList());

    }

    public List<String> printCalculatedSpecification() {
        List<Vehicle> vehicles = vehicleRepository.getVehicles();
        if (vehicles == null) return new ArrayList<>();

        int[] idx = { 1 };
        return vehicles.stream()
                .map(vehicle -> getOutputLine(idx[0]++, calculateSpecification(vehicle.getName(), vehicle.getSipp())))
                .collect(Collectors.toList());
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new HashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public List<String> printHighestRatedSupplierDESC() {
        List<Vehicle> vehicles = vehicleRepository.getVehicles();
        if (vehicles == null) return new ArrayList<>();

        int[] idx = { 1 };
        return vehicles.stream()
                .sorted(Comparator.comparing(Vehicle::getRating).reversed())
                .filter(distinctByKey(vehicle -> getCarType(vehicle.getSipp().substring(0,1))))
                .map(vehicle -> getOutputLine(idx[0]++, vehicle.getName(),getCarType(vehicle.getSipp().substring(0,1)), vehicle.getSupplier(), vehicle.getRating().toString()))
                .collect(Collectors.toList());
    }

    public List<String> printVehiclesByBrakeDownsDESC() {
        List<Vehicle> vehicles = vehicleRepository.getVehicles();
        if (vehicles == null) return new ArrayList<>();

        int[] idx = {1};
        return vehicles.stream()
                .map(vehicle -> {return new Object() {
                        String name = vehicle.getName();
                        BigDecimal score = getVehicleBrakedownScore(vehicle.getSipp());
                        BigDecimal rating = vehicle.getRating();
                        BigDecimal sum = score.add(rating);
                    };
                })
                .sorted((o1, o2) -> o2.sum.compareTo(o1.sum))
                .map(vehicle -> getOutputLine(idx[0]++, vehicle.name, vehicle.score.toString(), vehicle.rating.toString(), vehicle.sum.toString()))
                .collect(Collectors.toList());
    }

    BigDecimal getVehicleBrakedownScore(String sipp) {
        BigDecimal score = new BigDecimal(0);
        if(sipp.substring(2,3).equals("M")) score = score.add(BigDecimal.ONE);
        else score = score.add(BigDecimal.valueOf(5));

        if (sipp.substring(3,4).equals("R")) score = score.add(BigDecimal.valueOf(2));

        return score;
    }

    String[] calculateSpecification(String name, String sipp) {
        ArrayList<String> carData = new ArrayList<>();
        carData.add(name);
        carData.add(sipp);
        carData.add(getCarType(sipp.substring(0,1)));
        carData.add(getDors(sipp.substring(1,2)));
        carData.add(getTransmission(sipp.substring(2,3)));
        carData.add("Petrol");
        carData.add(getFuelAndAirCon(sipp.substring(3,4)));

        return carData.toArray(new String[]{});
    }

    String getOutputLine(int counter, String... data) {
        String resultStr = Arrays.asList(data)
                .stream()
//                .collect(Collectors.joining("}-{", "{", "}"));
                .collect(Collectors.joining("-"));
        return String.format(OUTPUT_TEMPLATE, counter+".", resultStr);
    }

    String getCarType(String letter) {
        switch (letter) {
            case "M": return "Mini";
            case "E": return "Economy";
            case "C": return "Compact";
            case "I": return "Intermediate";
            case "S": return "Standard";
            case "F": return "Full size";
            case "P": return "Premium";
            case "L": return "Luxury";
            case "X": return "Special";
            default: return "Mini";
        }
    }

    String getDors(String letter) {
        switch (letter) {
            case "B": return "2 doors";
            case "C": return "4 doors";
            case "D": return "5 doors";
            case "W": return "Estate";
            case "T": return "Convertible";
            case "F": return "SUV";
            case "P": return "Pick up";
            case "V": return "Passenger Van";
            default: return "2 doors";
        }
    }

    String getTransmission(String letter) {
        switch (letter) {
            case "M": return "Manual";
            case "A": return "Automatic";
            default: return "Manual";
        }
    }

    String getFuelAndAirCon(String letter) {
        switch (letter) {
            case "N": return "no AC";
            case "R": return "AC";
            default: return "no AC";
        }
    }
}
