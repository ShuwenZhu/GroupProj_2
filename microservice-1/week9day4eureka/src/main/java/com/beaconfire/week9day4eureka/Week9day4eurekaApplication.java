package com.beaconfire.week9day4eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Week9day4eurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week9day4eurekaApplication.class, args);
    }

}
