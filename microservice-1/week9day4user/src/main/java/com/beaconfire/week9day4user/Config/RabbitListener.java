package com.beaconfire.week9day4user.Config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("New message received from " + message.getMessageProperties().getConsumerQueue() + ": " + new String(message.getBody()));
    }
}
