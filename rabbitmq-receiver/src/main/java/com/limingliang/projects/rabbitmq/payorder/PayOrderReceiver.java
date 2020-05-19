package com.limingliang.projects.rabbitmq.payorder;

import com.limingliang.projects.rabbitmq.dictionary.MsgLogStatusEnum;
import com.limingliang.projects.rabbitmq.domain.MsgLog;
import com.limingliang.projects.rabbitmq.payorder.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/11 15:19
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */
@Slf4j
public class PayOrderReceiver {

    @Autowired
    private MsgLogService msgLogService;

    @RabbitListener(queues = "#{payOrderQueue.name}")
    @Transactional
    public void receive1(String message) throws InterruptedException {

        log.info(message);

        String msgId = message.split("messageId:")[1];

        MsgLog msgLog = msgLogService.getByMsgId(msgId);
        if (msgLog != null && MsgLogStatusEnum.ConsumerSuccess.getCode() == msgLog.getStatus()) {
            log.info("重复消费, msgId: {}", msgId);
            return;
        }

        boolean businessSuccess = true;

        if (businessSuccess) {

            MsgLog updateMsgLog = new MsgLog();
            updateMsgLog.setMsgId(msgId);
            updateMsgLog.setStatus(MsgLogStatusEnum.ConsumerSuccess.getCode());
            updateMsgLog.setUpdateTime(new Date());
            msgLogService.update(updateMsgLog);
        }
        //验证消息重新投递
        //int a = 1/0;
    }

}
