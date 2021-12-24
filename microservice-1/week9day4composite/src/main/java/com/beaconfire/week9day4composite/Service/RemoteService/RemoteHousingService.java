package com.beaconfire.week9day4composite.Service.RemoteService;

import com.beaconfire.week9day4composite.Domain.HousingService.House;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("housing-service")
public interface RemoteHousingService {

    @GetMapping("housing-service/getAllHouses")
    ResponseEntity<List<House>> getAllHouses();

    //i want to send a post request to  add a new house
}
