package com.limingliang.projects.rocketmq.practice.producer;

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
public class DelayProducer {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer =  new DefaultMQProducer("DelayProducerGroup");
        producer.setNamesrvAddr(MessageConstants.NAMESRV_ADDR + ":9876");
        producer.start();

        for (int i = 0; i < 5; i++) {

            Message message = new Message("DelayTopic", "TagA",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            //messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
            message.setDelayTimeLevel(4); //延迟10秒
            SendResult sendResult = producer.send(message);
            log.info(JSON.toJSONString(sendResult));
        }
        
        producer.shutdown();


    }
}
