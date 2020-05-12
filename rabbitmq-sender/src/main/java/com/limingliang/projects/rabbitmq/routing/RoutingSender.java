package com.limingliang.projects.rabbitmq.routing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
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
public class RoutingSender {

    @Autowired
    private DirectExchange directExchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    AtomicInteger index = new AtomicInteger(0);

    AtomicInteger count = new AtomicInteger(0);

    private final String[] keys = {"orange", "black", "green"};


    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {

        StringBuilder builder = new StringBuilder("Hello world");
        if (index.incrementAndGet() == 3) {
            index.set(0);
        }

        for (int i = 0; i < index.get() + 1; i++) {
            builder.append(".");
        }
        String key = keys[this.index.get()];
        builder.append(key).append(' ');
        builder.append(count.incrementAndGet());
        String message = builder.toString();

        this.rabbitTemplate.convertAndSend(directExchange.getName(), key, message);
        log.info("发送消息,内容为 " + message);
    }
}
