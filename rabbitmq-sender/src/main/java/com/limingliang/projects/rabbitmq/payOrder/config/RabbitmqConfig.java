package com.limingliang.projects.rabbitmq.payOrder.config;

import com.alibaba.fastjson.JSON;
import com.limingliang.projects.rabbitmq.dictionary.MsgLogStatusEnum;
import com.limingliang.projects.rabbitmq.domain.MsgLog;
import com.limingliang.projects.rabbitmq.payOrder.service.MsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Date;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/16 16:56
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */
@Slf4j
@Profile("pay_order")
@Configuration
public class RabbitmqConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private MsgLogService msgLogService;

    @Bean
    public RabbitTemplate rabbitTemplate() {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        // 消息是否发送到exchange
        rabbitTemplate.setConfirmCallback( ((correlationData, ack, cause) -> {

            log.info("correlationData" + JSON.toJSONString(correlationData));
            log.info("ack" + ack);
            log.info("cause" + cause);

            if (ack) {

                log.info("消息成功发送到Exchange");
                MsgLog msgLog = new MsgLog();
                msgLog.setMsgId(correlationData.getId());
                msgLog.setMsgId(correlationData.getId());
                msgLog.setStatus(MsgLogStatusEnum.DeliverSuccess.getCode());
                msgLog.setUpdateTime(new Date());
                msgLogService.update(msgLog);
            } else {
                log.info("消息发送到Exchange失败, {}, cause: {}", correlationData, cause);
            }

        }));

        // 发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        // 消息是否从Exchange路由到Queue, 注意: 这是一个失败回调, 只有消息从Exchange路由到Queue失败才会回调这个方法
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}",
                    exchange, routingKey, replyCode, replyText, message);
        });

        return rabbitTemplate;
    }

}
