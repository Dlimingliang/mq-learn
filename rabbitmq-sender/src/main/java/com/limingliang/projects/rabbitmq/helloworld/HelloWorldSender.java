package com.limingliang.projects.rabbitmq.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

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

    AtomicInteger dots = new AtomicInteger(0);

    AtomicInteger count = new AtomicInteger(0);


    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {

        StringBuilder builder = new StringBuilder("Hello world");
        if (dots.incrementAndGet() == 4) {
            dots.set(1);
        }

        for (int i = 0; i < dots.get(); i++) {
            builder.append(".");
        }
        builder.append(count.incrementAndGet());
        String message = builder.toString();

        this.rabbitTemplate.convertAndSend(queue.getName(), message);
        log.info("发送消息,内容为 " + message);
    }
}
