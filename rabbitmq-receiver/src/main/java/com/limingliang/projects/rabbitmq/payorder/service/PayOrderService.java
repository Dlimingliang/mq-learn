package com.limingliang.projects.rabbitmq.payorder.service;


import com.limingliang.projects.rabbitmq.constants.RoutingKeyConstants;
import com.limingliang.projects.rabbitmq.domain.MsgLog;
import com.limingliang.projects.rabbitmq.domain.PayOrder;
import com.limingliang.projects.rabbitmq.payorder.mapper.PayOrderMapper;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/14 16:40
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Service
@Transactional(readOnly = true)
public class PayOrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange payOrderExchange;

    @Autowired
    private MsgLogService msgLogService;

    @Autowired
    private PayOrderMapper payOrderMapper;

    public PayOrder getByOrderCode(String orderCode) {
        return payOrderMapper.getByOrderCode(orderCode);
    }

    @Transactional
    public boolean create(PayOrder payOrder){

        boolean result = payOrderMapper.create(payOrder) > 0;

        String msgId = UUID.randomUUID().toString();
        String message = "订单创建, 订单号: " + payOrder.getOrderCode() + " messageId:" + msgId;
        MsgLog msgLog = new MsgLog(msgId, message, payOrderExchange.getName(), RoutingKeyConstants.orderCreateRouting);
        msgLogService.create(msgLog);

        rabbitTemplate.convertAndSend(payOrderExchange.getName(), RoutingKeyConstants.orderCreateRouting,
                message, new CorrelationData(msgId));

        return result ;
    }

    @Transactional
    public boolean update(PayOrder payOrder) {

        //为了使该业务显得复杂
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return payOrderMapper.update(payOrder) > 0;
    }
}
