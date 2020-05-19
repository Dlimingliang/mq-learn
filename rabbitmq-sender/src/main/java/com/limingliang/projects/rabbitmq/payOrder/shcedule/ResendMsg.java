package com.limingliang.projects.rabbitmq.payOrder.shcedule;

import com.limingliang.projects.rabbitmq.payOrder.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/19 10:10
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Slf4j
@Component
public class ResendMsg {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MsgLogService msgLogService;

    // 最大投递次数
    private static final int MAX_TRY_COUNT = 3;


    /**
     * 每30s拉取投递失败的消息, 重新投递
     */
    @Scheduled(cron = "0/300 * * * * ?")
    public void resend() {

        log.info("开始执行定时任务(重新投递消息)");


        log.info("定时任务执行结束(重新投递消息)");
    }
}
