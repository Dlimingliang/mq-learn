package com.limingliang.projects.rocketmq.practice.producer;

import com.limingliang.projects.rocketmq.constants.MessageConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/6/16 19:00
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Slf4j
public class SubAreaSequenceProducer {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("SubAreaSequenceProducerGroup");
        producer.setNamesrvAddr(MessageConstants.NAMESRV_ADDR + ":9876");
        producer.start();

        String[] tags = new String[]{"TagA", "TagC", "TagD"};
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        // 订单列表
        List<OrderStep> orderList = SubAreaSequenceProducer.buildOrders();

        for (int i = 0; i < orderList.size(); i++) {

            String body = dateStr + "Hello RocketMQ " + orderList.get(i);
            Message message = new Message("SubAreaSequenceTopic", tags[i % tags.length],
                    "key" + i, body.getBytes());

            SendResult sendResult = producer.send(message, (mqs, msg, arg) -> {
                Long id = (Long) arg;  //根据订单id选择发送queue
                long index = id % mqs.size();
                return mqs.get((int) index);
            }, orderList.get(i).getOrderId());

            log.info(String.format("SendResult status:%s, queueId:%d, body:%s",
                    sendResult.getSendStatus(),
                    sendResult.getMessageQueue().getQueueId(),
                    body));
        }

        producer.shutdown();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class OrderStep {

        private Long orderId;
        private String desc;
    }

    private static List<OrderStep> buildOrders() {

        List<OrderStep> orderList = new ArrayList<OrderStep>();

        OrderStep orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("推送");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        return orderList;
    }
}
