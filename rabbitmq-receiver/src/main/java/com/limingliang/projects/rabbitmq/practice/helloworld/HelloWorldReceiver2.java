package com.limingliang.projects.rabbitmq.practice.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

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
public class HelloWorldReceiver2 {

    @RabbitHandler
    public void receive(String in) throws InterruptedException {

        StopWatch watch = new StopWatch();
        watch.start();
        doWork(in);
        watch.stop();
        log.info(this.getClass().getName() + "接收消息,内容为 " + in + "时间消耗为" + watch.getTotalTimeSeconds());
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(2000);
            }
        }
    }
}
