package com.limingliang.projects.rabbitmq.service;

import com.limingliang.projects.rabbitmq.constants.RoutingKeyConstants;
import com.limingliang.projects.rabbitmq.domain.MsgLog;
import com.limingliang.projects.rabbitmq.domain.PayOrder;
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
 * @Date: 2020/6/11 11:52
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Service
public class SendMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange payOrderExchange;

//    @Autowired
//    private TopicExchange payOrderDelayExchange;

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private MsgLogService msgLogService;

    @Transactional
    public void sendMessage(PayOrder payOrder) {

        payOrderService.create(payOrder);

        String msgId = UUID.randomUUID().toString();
        String messageBody = "订单创建, 订单号:" + payOrder.getOrderCode();
        MsgLog msgLog = new MsgLog(msgId, messageBody, payOrderExchange.getName(), RoutingKeyConstants.ORDER_CREATE_ROUTING_KEY);
        msgLogService.create(msgLog);

        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(payOrderExchange.getName(), RoutingKeyConstants.ORDER_CREATE_ROUTING_KEY,
                messageBody, correlationData);

        //发送延迟消息
//        rabbitTemplate.convertAndSend(payOrderDelayExchange.getName(),
//                RoutingKeyConstants.ORDER_CREATE_ROUTING_KEY,
//                messageBody,
//                (Message message) -> {message.getMessageProperties().setDelay(10000); return message;},
//                correlationData);
    }
}
