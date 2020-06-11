package com.limingliang.projects.rabbitmq.receiver;

import com.limingliang.projects.rabbitmq.dictionary.OrderStatusEnum;
import com.limingliang.projects.rabbitmq.domain.PayOrder;
import com.limingliang.projects.rabbitmq.service.PayOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/11 15:19
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */
@Slf4j
public class PayOrderReceiver {

    @Autowired
    private PayOrderService payOrderService;

    @RabbitListener(queues = "#{payOrderQueue.name}")
    @Transactional
    public void receive(String message) throws InterruptedException {

        log.info("订单消费者消费消息:" + message);

        String orderCode = message.split(":")[1];

        PayOrder payOrder = new PayOrder();
        payOrder.setOrderCode(orderCode);
        payOrder.setStatus(OrderStatusEnum.PayAlready.getCode());
        payOrderService.update(payOrder);
        //验证消息重新投递
        //int a = 1/0;
    }

    @RabbitListener(queues = "#{payOrderDelayQueue.name}")
    @Transactional
    public void delayReceive(String message) throws InterruptedException {

        log.info("订单消费者延迟消费消息:" + message);
    }

}
