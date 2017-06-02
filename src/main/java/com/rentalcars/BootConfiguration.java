package com.rentalcars;

/**
 * Created by Sergey on 02-Jun-17.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:boot.properties")
public class BootConfiguration {
}
