package com.limingliang.projects.rocketmq.receiver;

import com.limingliang.projects.rocketmq.domain.PayOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/6/30 16:12
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Slf4j
@RocketMQMessageListener(topic = "order_topic", consumerGroup = "orderConsumerGroup")
public class PayOrderReceiver implements RocketMQListener<PayOrder> {

    @Override
    public void onMessage(PayOrder payOrder) {

        log.info("接收到消息: {}", payOrder);

//        PayOrder payOrder = new PayOrder();
//        payOrder.setOrderCode(orderCode);
//        payOrder.setStatus(OrderStatusEnum.PayAlready.getCode());
//        payOrderService.update(payOrder);
    }
}
