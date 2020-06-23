package com.limingliang.projects.rocketmq.practice.consumer;

import com.limingliang.projects.rocketmq.constants.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/6/16 17:19
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Slf4j
public class Consumer {

    public static void main(String[] args) throws Exception {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroup");
        consumer.setNamesrvAddr(MessageConstants.NAMESRV_ADDR + ":9876");
        consumer.subscribe("TopicTest", "*");

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
           log.info(String.format("%s Receive New Messages: %s", Thread.currentThread().getName(), msgs));
            // 标记该消息已经被成功消费
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
        log.info("Consumer Started.");
    }
}
