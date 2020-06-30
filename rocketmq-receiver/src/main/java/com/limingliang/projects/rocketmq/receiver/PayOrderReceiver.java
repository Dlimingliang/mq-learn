package com.limingliang.projects.rocketmq.receiver;

import com.limingliang.projects.rocketmq.dictionary.OrderStatusEnum;
import com.limingliang.projects.rocketmq.domain.PayOrder;
import com.limingliang.projects.rocketmq.service.PayOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/6/30 16:12
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Slf4j
@RocketMQMessageListener(topic = "order_topic", selectorExpression = "create", consumerGroup = "orderConsumerGroup")
public class PayOrderReceiver implements RocketMQListener<PayOrder>, RocketMQPushConsumerLifecycleListener {

    @Autowired
    private PayOrderService payOrderService;

    @Override
    @Transactional
    public void onMessage(PayOrder payOrder) {

        log.info("orderConsumerGroup接收到消息: {}", payOrder);

        PayOrder updateOrder = new PayOrder();
        updateOrder.setOrderCode(payOrder.getOrderCode());
        updateOrder.setStatus(OrderStatusEnum.PayAlready.getCode());
        payOrderService.update(updateOrder);

        //int a = 1/0;
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
    }
}
