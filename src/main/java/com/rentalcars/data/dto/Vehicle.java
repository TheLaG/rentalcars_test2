package com.rentalcars.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by Sergey on 02-Jun-17.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    private String sipp;
    private String name;
    private BigDecimal price;
    private String supplier;
    private BigDecimal rating;
}
