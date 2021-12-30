package com.beaconfire.week9day4composite.Service.RemoteService;

import com.beaconfire.week9day4composite.Domain.MangoDBobj.TimesheetRecord;
import com.beaconfire.week9day4composite.Domain.MangoDBobj.UserContact;
import com.beaconfire.week9day4composite.Domain.responseObjects.ResponseMsg;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@FeignClient("housing-service")
public interface RemoteHousingService {

    @GetMapping("usercontact/getUserContact")
    public ResponseEntity<Optional<UserContact>> getUserContact(@RequestHeader Map<String, String> headers,@RequestParam("userId") Integer userId);

    @GetMapping("usercontact/getAll")
    public ResponseEntity<List<UserContact>> getAll();

    @PostMapping("usercontact/update")
	public ResponseMsg updateUserContact(
			@RequestHeader Map<String, String> headers, 
			@RequestParam("user") UserContact user);
	
}
