package com.limingliang.projects.rabbitmq.topics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
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
public class TopicSender {

    @Autowired
    private TopicExchange topicExchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    AtomicInteger index = new AtomicInteger(0);

    AtomicInteger count = new AtomicInteger(0);

    private final String[] keys = {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};


    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {

        StringBuilder builder = new StringBuilder();
        if (index.incrementAndGet() == keys.length) {
            index.set(0);
        }

        String key = keys[this.index.get()];
        builder.append(key).append(' ');
        builder.append(count.incrementAndGet());
        String message = builder.toString();

        this.rabbitTemplate.convertAndSend(topicExchange.getName(), key, message);
        log.info("发送消息,内容为 " + message);
    }
}
