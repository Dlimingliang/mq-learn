package com.limingliang.projects.rocketmq.receiver;

import com.limingliang.projects.rocketmq.domain.PayOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/6/30 17:28
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Slf4j
@RocketMQMessageListener(topic = "order_topic", selectorExpression = "create", consumerGroup = "discountCardConsumerGroup")
public class DiscountCardReceiver implements RocketMQListener<PayOrder>, RocketMQPushConsumerLifecycleListener {


    @Override
    public void onMessage(PayOrder payOrder) {

        log.info("DiscountCardConsumerGroup接收到消息: {}", payOrder);

        //int a = 1 / 0;
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
    }
}
