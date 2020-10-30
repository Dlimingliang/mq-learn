package com.limingliang.projects.rocketmq.practice.consumer;

import com.limingliang.projects.rocketmq.constants.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.AllocateMessageQueueStrategy;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/6/16 17:19
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Slf4j
public class DivideByQueueConsumer {

    public static void main(String[] args) throws Exception {

        AllocateMessageQueueStrategy allocateMessageQueueStrategy = new AllocateMessageQueueStrategy() {
            @Override
            public List<MessageQueue> allocate(String consumerGroup, String currentCID, List<MessageQueue> queueList,
                                               List<String> cidAll) {
                List<MessageQueue> list = new ArrayList<>();
                list.add(queueList.get(2));
                return list;
            }

            @Override
            public String getName() {
                return "divide_by_queue";
            }
        };

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("DivideConsumerGroup",
                null, allocateMessageQueueStrategy);
        consumer.setNamesrvAddr(MessageConstants.NAMESRV_ADDR + ":9876");
        consumer.subscribe("divide_test_queue", "*");

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
           log.info(String.format("%s Receive New Messages: %s", Thread.currentThread().getName(), msgs));
            // 标记该消息已经被成功消费
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
        log.info("Consumer Started.");
    }
}
