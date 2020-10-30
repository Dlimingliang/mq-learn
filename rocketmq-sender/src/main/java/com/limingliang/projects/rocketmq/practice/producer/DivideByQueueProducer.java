package com.limingliang.projects.rocketmq.practice.producer;

import com.limingliang.projects.rocketmq.constants.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/6/16 16:43
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Slf4j
public class DivideByQueueProducer {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("DivideProducerGroup");
        producer.setNamesrvAddr(MessageConstants.NAMESRV_ADDR + ":9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        int messageCount = 5;
        final CountDownLatch2 countDownLatch = new CountDownLatch2(messageCount);

        for (int i = 0; i < messageCount; i++) {

            final int index = i;
            Message message = new Message("divide_test_queue", "TagA", "OrderID188",
                    ("Hello RocketMQ " + index).getBytes(RemotingHelper.DEFAULT_CHARSET));

            MessageQueueSelector queueSelector = new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> queueList, Message message, Object o) {
                    return queueList.get(1);
                }
            };

            producer.send(message, queueSelector, index);
        }

        countDownLatch.await(5, TimeUnit.SECONDS);
        producer.shutdown();
    }
}
