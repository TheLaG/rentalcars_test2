package com.rentalcars;

import com.rentalcars.data.VehicleRepository;
import com.rentalcars.data.dto.Vehicle;
import com.rentalcars.services.VehicleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by Sergey on 02-Jun-17.
 */

@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) throws MalformedURLException {
        SpringApplication.run(Application.class, args);
    }
}
