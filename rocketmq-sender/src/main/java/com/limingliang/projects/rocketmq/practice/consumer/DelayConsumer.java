package com.limingliang.projects.rocketmq.practice.consumer;

import com.limingliang.projects.rocketmq.constants.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/6/16 17:19
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Slf4j
public class DelayConsumer {

    public static void main(String[] args) throws Exception {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("DelayConsumerGroup");
        consumer.setNamesrvAddr(MessageConstants.NAMESRV_ADDR + ":9876");
        consumer.subscribe("DelayTopic", "*");

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {

            for (MessageExt message : msgs) {
                log.info("Receive message[msgId=" + message.getMsgId() + "] " + (System.currentTimeMillis() - message.getStoreTimestamp()) + "ms later");
            }
            // 标记该消息已经被成功消费
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
        log.info("Consumer Started.");
    }
}
