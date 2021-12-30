package com.beaconfire.week9day4user.scheduling;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.beaconfire.week9day4user.Domain.MangoDBobj.Timesheet;
import com.beaconfire.week9day4user.Domain.MangoDBobj.TimesheetRecord;
import com.beaconfire.week9day4user.Service.TimesheetService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTask {

//    private RabbitTemplate rabbitTemplate;
//
//    @Autowired
//    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
	
	@Autowired
    TimesheetService timesheetService;

    @Scheduled(cron = "0 0 * * * Sat") //0 0 * * * Sat
    public void sendMessageEverySat(){
    	LocalDateTime now = LocalDateTime.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formatDateTime = now.format(formatter);
        
        List<Timesheet> defaultList = new ArrayList<>();
        
        for (int i = 5; i > 0; i--)
        {
        	defaultList.add(Timesheet.builder()
            		.date(now.minusDays(i).format(formatter))
            		.startTime("09:00:00")
            		.endTime("18:00:00")
            		.isFloating(false)
            		.isHoliday(false)
            		.isVacation(false)
            		.build());
        }
        
        
        TimesheetRecord newdefaultR = TimesheetRecord.builder()
        		._id("61cbf0b409ffda1f6e200c0b")
        		.userId(-1)
                .approvalStatus("N/A")
                .submissionStatus("Not Started")
                .weekEnding(formatDateTime)
                .billingHour(40)
                .compensatedHour(40)
                .timesheet(defaultList)
                .build();
        timesheetService.update(newdefaultR);
       	System.out.println("DefaultSent!!!");
       
    }
}
