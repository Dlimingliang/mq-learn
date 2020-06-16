package com.limingliang.projects.rocketmq.practice;

import com.alibaba.fastjson.JSON;
import com.limingliang.projects.rocketmq.constants.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/6/15 14:27
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Slf4j
public class SyncProducer {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer =  new DefaultMQProducer("SyncProducerGroup");
        producer.setNamesrvAddr(MessageConstants.NAMESRV_ADDR + ":9876");
        producer.start();

        Message message = new Message("TopicTest", "TagA",
                ("Hello RocketMQ ").getBytes(RemotingHelper.DEFAULT_CHARSET));

        SendResult sendResult = producer.send(message);
        log.info(JSON.toJSONString(sendResult));

        producer.shutdown();


    }
}
