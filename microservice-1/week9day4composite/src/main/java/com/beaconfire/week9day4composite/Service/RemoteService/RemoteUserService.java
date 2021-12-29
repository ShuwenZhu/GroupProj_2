package com.beaconfire.week9day4composite.Service.RemoteService;

import com.beaconfire.week9day4composite.Domain.MangoDBobj.TimesheetRecord;
import com.beaconfire.week9day4composite.Domain.UserService.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@FeignClient("user-service")
public interface RemoteUserService {

    @GetMapping("timesheet/getUserWE")
    ResponseEntity<Optional<TimesheetRecord>> getUserWERecord(@RequestHeader Map<String, String> headers, @RequestParam("userId") Integer userId,@RequestParam("weDate") String weDate);

    @GetMapping("timesheet/getUserWEByUserId")
    public ResponseEntity<Optional<List<TimesheetRecord>>> getUserWERecord(@RequestHeader Map<String, String> headers, @RequestParam Integer userId);

    @PostMapping("timesheet/updateList")
    public void updateTimesheetList(@RequestParam("ts") List<TimesheetRecord> ts);
    
//
//    @PostMapping("xxxxx")
//    ResponseEntity<List<User>> addUser(@RequestBody User user);
//
//    @GetMapping("xxxxxxxx/{userId}")
//    ResponseEntity<List<User>> getByID(@PathVariable Integer id, @RequestHeader("Authorization") String token);

}
