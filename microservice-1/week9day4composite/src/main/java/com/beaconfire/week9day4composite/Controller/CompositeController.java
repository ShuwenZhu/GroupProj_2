package com.beaconfire.week9day4composite.Controller;

import com.beaconfire.week9day4composite.Domain.UserWEDateDetailPack;
import com.beaconfire.week9day4composite.Domain.UserWEDetail;
import com.beaconfire.week9day4composite.Service.CompositeService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("composite-service")
@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
public class CompositeController {

    private CompositeService compositeService;

    @Autowired
    public void setCompositeService(CompositeService compositeService) {
        this.compositeService = compositeService;
    }

    @GetMapping("getWeekEndInfo")
    public ResponseEntity<UserWEDetail> getWEDetail(@RequestHeader Map<String, String> headers, String weDate, Integer userId){
        return ResponseEntity.ok(compositeService.getWEDetail(headers, weDate, userId));
    }
    
    @GetMapping("getWeekEndListWithDayInfo")
    public ResponseEntity<UserWEDateDetailPack> getWEListDetail(@RequestHeader Map<String, String> headers, Integer userId){
        return ResponseEntity.ok().body(compositeService.getWEListDetail(headers, userId));
    }
}
