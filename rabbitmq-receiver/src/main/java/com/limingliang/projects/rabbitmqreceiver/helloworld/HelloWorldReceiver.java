package com.limingliang.projects.rabbitmqreceiver.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/9 16:18
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Slf4j
@Profile({"hello_world"})
@Component
@RabbitListener(queues = "hello_world")
public class HelloWorldReceiver {

    @RabbitHandler
    public void receive(String in) {

        log.info("接收消息,内容为 " + in);
    }
}
