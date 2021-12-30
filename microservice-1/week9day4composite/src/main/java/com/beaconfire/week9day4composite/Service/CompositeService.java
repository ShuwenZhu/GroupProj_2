package com.beaconfire.week9day4composite.Service;

import com.beaconfire.week9day4composite.Domain.MangoDBobj.Timesheet;
import com.beaconfire.week9day4composite.Domain.MangoDBobj.TimesheetRecord;
import com.beaconfire.week9day4composite.Domain.MangoDBobj.UserContact;
import com.beaconfire.week9day4composite.Domain.UserHouse;
import com.beaconfire.week9day4composite.Domain.UserWEDateDetailPack;
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
        
        if (weRecord.isEmpty())
        	System.out.println("record not found");
        if (userContact.isEmpty())
        	System.out.println("user not found");
        
        
        if (weRecord.isPresent() && userContact.isPresent())
        	return UserWEDetail.builder().record(weRecord.get())
        					  .maxFloatDays(userContact.get().getMaxFloatDays())
        					  .maxVacationDays(userContact.get().getMaxVacationDays())
        					  .usedFloatDays(userContact.get().getUsedFloatDays())
        					  .usedVacationDays(userContact.get().getUsedVacationDays()).build();
        return null;
    }

	public UserWEDateDetailPack getWEListDetail(Map<String, String> headers, Integer userId) {
		Optional<List<TimesheetRecord>> weRecordList = remoteUserService.getUserWERecord(headers, userId).getBody();
		Optional<UserContact> userContact = remoteHousingService.getUserContact(headers, userId).getBody();
		
		if (weRecordList.isEmpty())
        	System.out.println("record not found");
        if (userContact.isEmpty())
        	System.out.println("user not found");
        
        
        if (weRecordList.isPresent() && userContact.isPresent())
        {
        	return UserWEDateDetailPack.builder().records(weRecordList.get())
        				.maxFloatDays(userContact.get().getMaxFloatDays())
        				.maxVacationDays(userContact.get().getMaxVacationDays())
					  	.usedFloatDays(userContact.get().getUsedFloatDays())
					  	.usedVacationDays(userContact.get().getUsedVacationDays()).build();
        }
		
		return null;
	}
	
	public boolean AssignDefaultTimesheet(TimesheetRecord drecord)
	{
//		List<UserContact> userContactlist = remoteHousingService.getAll().getBody();
//		List<TimesheetRecord> records = new ArrayList<>();
//		for (UserContact c : userContactlist)
//		{
//			drecord.setUserId(c.getUserId());
//			records.add(drecord);
//		}
//		records.add(drecord);
//		remoteUserService.updateTimesheet(drecord);
		return true;
	}

	public String approve(Map<String, String> headers, Integer userId, String date, String status) {
//		Optional<UserContact> userContact = remoteHousingService.getUserContact(headers, userId).getBody();
//		Optional<TimesheetRecord> weRecord = remoteUserService.getUserWERecord(headers, userId, date).getBody();
//		
//		if (userContact.isEmpty()||weRecord.isEmpty()) return "Record not match to the database";
//		UserContact user = userContact.get();
//		TimesheetRecord record = weRecord.get();
//		int floatDayCount = 0, vacationDayCount = 0;
//		for (Timesheet t : record.getTimesheet())
//		{
//			if(t.isFloating) floatDayCount++;
//			if(t.isVacation) vacationDayCount++;
//		}
//		
//		if (floatDayCount <= user.getMaxFloatDays() - user.getUsedFloatDays() &&
//			vacationDayCount <= user.getMaxVacationDays() - user.getUsedVacationDays())
//		{
//			//update user info with new date remaining
////			user.setUsedFloatDays(user.getUsedFloatDays() + floatDayCount);
////			user.setUsedVacationDays(user.getUsedVacationDays() + vacationDayCount);
////			remoteHousingService.updateUserContact(headers, user);
//			//approve at timesheet db
//			remoteUserService.changeTimesheetApprovalStatus(headers, userId, date, status);
//			System.out.println("doneeeeeeeeeeeeeeeee");
//			return "Successfully Approved!";
//		} else
//		{
//			//reject at timesheet db
//			remoteUserService.changeTimesheetApprovalStatus(headers, userId, date, "Not Approved");
//			return "Approve operation failed: " +
//			((floatDayCount + user.getUsedFloatDays() > user.getMaxFloatDays())? 
//					"Not enough floating day":"Not enough vacation day");
//		}
		
		return null;
	}

}
