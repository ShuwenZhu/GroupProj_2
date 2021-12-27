package com.beaconfire.week9day4composite.Service.RemoteService;

import com.beaconfire.week9day4composite.Domain.MangoDBobj.UserContact;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;
import java.util.Optional;

@FeignClient("housing-service")
public interface RemoteHousingService {

    @GetMapping("usercontact/getUserContact")
    public ResponseEntity<Optional<UserContact>> getUserContact(@RequestHeader Map<String, String> headers, Integer userId);

    //i want to send a post request to  add a new house
}
