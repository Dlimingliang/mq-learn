package com.limingliang.projects.rabbitmq.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/20 14:58
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Slf4j
public class DsicountCardReceiver {

    @RabbitListener(queues = "#{orderCreateDiscountCardQueue.name}")
    public void receiver(String message) {

        log.info("优惠券消费者消费消息:" + message);

        //为了显示业务复杂
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //验证消息重新投递
//        int a = 1/0;
    }
}
