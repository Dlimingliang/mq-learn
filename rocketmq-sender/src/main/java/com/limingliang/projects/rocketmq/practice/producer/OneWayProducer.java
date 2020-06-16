package com.limingliang.projects.rocketmq.practice.producer;

import com.limingliang.projects.rocketmq.constants.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/6/15 14:27
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Slf4j
public class OneWayProducer {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer =  new DefaultMQProducer("OneWayProducerGroup");
        producer.setNamesrvAddr(MessageConstants.NAMESRV_ADDR + ":9876");
        producer.start();

        for (int i = 0; i < 5; i++) {

            Message message = new Message("TopicTest", "TagA",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.sendOneway(message);
        }
        
        producer.shutdown();


    }
}
