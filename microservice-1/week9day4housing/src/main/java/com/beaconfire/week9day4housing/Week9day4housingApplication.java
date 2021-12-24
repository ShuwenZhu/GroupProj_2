package com.beaconfire.week9day4housing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableMongoRepositories
public class Week9day4housingApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week9day4housingApplication.class, args);
    }

}
