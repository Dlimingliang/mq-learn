package com.limingliang.projects.rabbitmqreceiver.helloworld;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/9 16:18
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Profile({"hello_world"})
@RabbitListener(queues = "hello_world")
public class HelloWorldReceiver {

    @RabbitHandler
    public void receive(String in) {

        System.out.println(" [x] Received '" + in + "'");
    }
}
