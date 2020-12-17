package com.seesea.seeseabilling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SeeseaBillingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeeseaBillingApplication.class, args);
    }

}
