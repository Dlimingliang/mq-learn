package com.limingliang.projects.rabbitmqsender.helloworld;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class HelloWorldSender {

    @Autowired
    private Queue queue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 10000, initialDelay = 500)
    public void send() {

        String message = "Hello World!";
        this.rabbitTemplate.convertAndSend(queue.getName(), message);
        log.info("发送消息,内容为" + message);
    }
}
