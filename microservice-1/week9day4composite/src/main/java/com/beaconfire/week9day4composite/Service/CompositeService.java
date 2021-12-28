package com.beaconfire.week9day4composite.Service;

import com.beaconfire.week9day4composite.Domain.HousingService.House;
import com.beaconfire.week9day4composite.Domain.MangoDBobj.TimesheetRecord;
import com.beaconfire.week9day4composite.Domain.MangoDBobj.UserContact;
import com.beaconfire.week9day4composite.Domain.UserHouse;
import com.beaconfire.week9day4composite.Domain.UserWEDetail;
import com.beaconfire.week9day4composite.Domain.UserService.User;
import com.beaconfire.week9day4composite.Service.RemoteService.RemoteHousingService;
import com.beaconfire.week9day4composite.Service.RemoteService.RemoteUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CompositeService {

    private RemoteUserService remoteUserService;
    private RemoteHousingService remoteHousingService;

    private CompositeService(RemoteUserService remoteUserService, RemoteHousingService remoteHousingService){
        this.remoteHousingService = remoteHousingService;
        this.remoteUserService = remoteUserService;
    }

    public UserWEDetail getWEDetail(Map<String, String> headers, String weDate, Integer userId){
    	Optional<TimesheetRecord> weRecord = remoteUserService.getUserWERecord(headers, userId, weDate).getBody();
        Optional<UserContact> userContact = remoteHousingService.getUserContact(headers, userId).getBody();
        
        
        if (weRecord.isPresent() && userContact.isPresent())
        	return UserWEDetail.builder().record(weRecord.get())
        					  .maxFloatDays(userContact.get().getMaxFloatDays())
        					  .maxVacationDays(userContact.get().getMaxVacationDays())
        					  .usedFloatDays(userContact.get().getUsedFloatDays())
        					  .usedVacationDays(userContact.get().getUsedVacationDays()).build();
        return null;
    }

}
