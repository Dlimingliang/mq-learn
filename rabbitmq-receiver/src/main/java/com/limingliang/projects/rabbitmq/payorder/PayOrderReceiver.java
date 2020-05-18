package com.limingliang.projects.rabbitmq.payorder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/11 15:19
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */
@Slf4j
public class PayOrderReceiver {

    @RabbitListener(queues = "#{payOrderQueue.name}")
    public void receive1(String message) throws InterruptedException {

        log.info(message);
    }

}
