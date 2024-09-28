package com.example.bootsend.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRabbitConfig {
    @Bean
    public Queue bootQueue() {
        return new Queue("bootqueue");
    }
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }
    @Bean
    public Binding binding(){
        return new Binding("bootqueue",Binding.DestinationType.QUEUE,"directExchange","bootKey", null);
    }
}
