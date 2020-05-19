package com.limingliang.projects.rabbitmq.payOrder.shcedule;

import com.limingliang.projects.rabbitmq.dictionary.MsgLogStatusEnum;
import com.limingliang.projects.rabbitmq.domain.MsgLog;
import com.limingliang.projects.rabbitmq.payOrder.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
    private static final int MAX_TRY_COUNT = 2;


    /**
     * 每30s拉取投递失败的消息, 重新投递
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void resend() {

        log.info("开始执行定时任务(重新投递消息)");
        List<MsgLog> msgLogs = msgLogService.selectTimeoutMsg();

        msgLogs.forEach(msgLog -> {

            String msgId = msgLog.getMsgId();
            if (msgLog.getTryCount() > MAX_TRY_COUNT) {

                MsgLog updateMsgLog = new MsgLog();
                updateMsgLog.setMsgId(msgId);
                updateMsgLog.setUpdateTime(new Date());
                updateMsgLog.setStatus(MsgLogStatusEnum.DeliverFail.getCode());
                msgLogService.update(updateMsgLog);
                log.info("超过最大重试次数, 消息投递失败, msgId: {}", msgId);
            } else {

                LocalDateTime localDateTime = LocalDateTime.now();
                MsgLog updateMsgLog = new MsgLog();
                updateMsgLog.setMsgId(msgId);
                updateMsgLog.setTryCount(msgLog.getTryCount() + 1);
                updateMsgLog.setUpdateTime(new Date());
                updateMsgLog.setNextTryTime(Date.from(localDateTime.plusMinutes(1L)
                        .atZone( ZoneId.systemDefault()).toInstant()));
                msgLogService.update(updateMsgLog);
                log.info("第 " + updateMsgLog.getTryCount() + " 次重新投递消息");
            }
        });


        log.info("定时任务执行结束(重新投递消息)");
    }
}
