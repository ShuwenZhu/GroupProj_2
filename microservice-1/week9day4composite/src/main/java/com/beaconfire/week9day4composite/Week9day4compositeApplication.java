package com.beaconfire.week9day4composite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Week9day4compositeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week9day4compositeApplication.class, args);
    }

}
