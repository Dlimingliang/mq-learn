package com.limingliang.projects.rabbitmqsender.helloworld;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/9 15:55
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */
public class HelloWorldSender {

    @Autowired
    private Queue queue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {

        String message = "Hello World!";
        this.rabbitTemplate.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
