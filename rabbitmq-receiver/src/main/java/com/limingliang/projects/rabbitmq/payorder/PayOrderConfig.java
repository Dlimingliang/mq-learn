package com.limingliang.projects.rabbitmq.payorder;

import com.limingliang.projects.rabbitmq.constants.QueueConstants;
import com.limingliang.projects.rabbitmq.constants.RoutingKeyConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/18 13:14
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Profile("pay_order")
@Configuration
public class PayOrderConfig {

    @Bean
    public Queue payOrderQueue() {

        return new Queue(QueueConstants.orderCreateQueue, true);
    }

    @Bean
    public Binding binding(TopicExchange payOrderExchange, Queue payOrderQueue) {

        return BindingBuilder.bind(payOrderQueue).to(payOrderExchange).with(RoutingKeyConstants.orderCreateRouting);
    }

    @Bean
    public PayOrderReceiver payOrderReceiver() {
        return new PayOrderReceiver();
    }
}
