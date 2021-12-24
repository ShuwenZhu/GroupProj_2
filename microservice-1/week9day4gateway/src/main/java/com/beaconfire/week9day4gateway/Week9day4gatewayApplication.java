package com.beaconfire.week9day4gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Week9day4gatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week9day4gatewayApplication.class, args);
    }

}
