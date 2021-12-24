package com.beaconfire.week9day4user.scheduling;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.beaconfire.week9day4user.Domain.MangoDBobj.TimesheetRecord;

import java.time.LocalDateTime;

@Component
public class ScheduledTask {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(cron = "0 0 * * * Sat") //0 0 * * * Sat
    public void sendMessageEverySecond(){
        TimesheetRecord newMessage = TimesheetRecord.builder()
                .approvalStatus("Scheduled Message")
                .attachment("Current local date time: " + LocalDateTime.now())
                .build();
        rabbitTemplate.convertAndSend("amq.direct", "q1", newMessage.toString());
    }
}
