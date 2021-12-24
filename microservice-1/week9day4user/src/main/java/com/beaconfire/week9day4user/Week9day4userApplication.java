package com.beaconfire.week9day4user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableMongoRepositories
@EnableScheduling
public class Week9day4userApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week9day4userApplication.class, args);
    }

}
