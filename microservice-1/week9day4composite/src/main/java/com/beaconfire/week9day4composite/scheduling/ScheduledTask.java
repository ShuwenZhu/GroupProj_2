package com.beaconfire.week9day4composite.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.beaconfire.week9day4composite.Domain.MangoDBobj.Timesheet;
import com.beaconfire.week9day4composite.Domain.MangoDBobj.TimesheetRecord;
import com.beaconfire.week9day4composite.Service.CompositeService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTask {
	
//	private CompositeService compositeService;
//
//    @Autowired
//    public void setCompositeService(CompositeService compositeService) {
//        this.compositeService = compositeService;
//    }
//
//    @Scheduled(cron = "0 */1 * * * Tue") //0 0 * * * Sat
//    public void sendMessageEverySat(){
//    	LocalDateTime now = LocalDateTime.now();
//    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        String formatDateTime = now.format(formatter);
//        
//        List<Timesheet> defaultList = new ArrayList<>();
//        
//        for (int i = 5; i > 0; i--)
//        {
//        	defaultList.add(Timesheet.builder()
//            		.date(now.minusDays(i).format(formatter))
//            		.startTime("9:00")
//            		.endTime("18:00")
//            		.isFloating(false)
//            		.isHoliday(false)
//            		.isVacation(false)
//            		.build());
//        }
//        
//        
//        TimesheetRecord newdefaultR = TimesheetRecord.builder()
//                .approvalStatus("N/A")
//                .submissionStatus("Not Started")
//                .weekEnding(formatDateTime)
//                .billingHour(40)
//                .compensatedHour(40)
//                .timesheet(defaultList)
//                .build();
//        if (compositeService.AssignDefaultTimesheet(newdefaultR))
//        	System.out.println("AllDefaultSent!!!");
//        else
//        	System.out.println("Error in scheduled task!!!");
//    }
}
