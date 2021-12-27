package com.beaconfire.week9day4composite.Service.RemoteService;

import com.beaconfire.week9day4composite.Domain.MangoDBobj.TimesheetRecord;
import com.beaconfire.week9day4composite.Domain.UserService.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient("user-service")
public interface RemoteUserService {

    @GetMapping("timesheet/getUserWE")
    ResponseEntity<Optional<TimesheetRecord>> getUserWERecord(@RequestParam Integer userId,@RequestParam String weDate);

//
//    @PostMapping("xxxxx")
//    ResponseEntity<List<User>> addUser(@RequestBody User user);
//
//    @GetMapping("xxxxxxxx/{userId}")
//    ResponseEntity<List<User>> getByID(@PathVariable Integer id, @RequestHeader("Authorization") String token);

}
