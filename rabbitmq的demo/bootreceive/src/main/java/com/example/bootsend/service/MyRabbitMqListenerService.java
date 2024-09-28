package com.example.bootsend.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MyRabbitMqListenerService {

    @RabbitListener(queues = {"bootqueue"})
    public void setAmqpTemplate(String message){
        System.out.println(message);
    }
}
