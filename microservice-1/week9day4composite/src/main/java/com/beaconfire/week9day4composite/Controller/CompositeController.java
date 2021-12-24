package com.beaconfire.week9day4composite.Controller;

import com.beaconfire.week9day4composite.Service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("composite-service")
public class CompositeController {

    private CompositeService compositeService;

    @Autowired
    public void setCompositeService(CompositeService compositeService) {
        this.compositeService = compositeService;
    }

    @GetMapping("getAllUserHousing")
    public ResponseEntity getAllUserHousing(){
        return ResponseEntity.ok(compositeService.getAllUserHouse());
    }
}
