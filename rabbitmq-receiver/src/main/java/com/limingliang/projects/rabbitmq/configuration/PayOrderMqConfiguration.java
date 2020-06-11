package com.limingliang.projects.rabbitmq.configuration;

import com.limingliang.projects.rabbitmq.constants.DeadLetterKeyConstants;
import com.limingliang.projects.rabbitmq.constants.ExchangeConstants;
import com.limingliang.projects.rabbitmq.constants.QueueConstants;
import com.limingliang.projects.rabbitmq.constants.RoutingKeyConstants;
import com.limingliang.projects.rabbitmq.receiver.DsicountCardReceiver;
import com.limingliang.projects.rabbitmq.receiver.PayOrderReceiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/18 13:14
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Profile("pay_order")
@Configuration
public class PayOrderMqConfiguration {

    @Bean
    public Queue payOrderQueue() {

        Map<String, Object> params = new HashMap<>();
        params.put(DeadLetterKeyConstants.EXCHANGE_KEY, ExchangeConstants.COMMON_DEAD_LETTER_DIRECT_EXCHANGE);
        params.put(DeadLetterKeyConstants.ROUTING_KEY, RoutingKeyConstants.COMMON_DEAD_LETTER_ROUTING_KEY);
        return new Queue(QueueConstants.ORDER_CREATE_QUEUE, true, false, false, params);
    }

    @Bean
    public Queue orderCreateDiscountCardQueue() {

        Map<String, Object> params = new HashMap<>();
        params.put(DeadLetterKeyConstants.EXCHANGE_KEY, ExchangeConstants.COMMON_DEAD_LETTER_DIRECT_EXCHANGE);
        params.put(DeadLetterKeyConstants.ROUTING_KEY, RoutingKeyConstants.COMMON_DEAD_LETTER_ROUTING_KEY);
        return new Queue(QueueConstants.ORDER_CREATE_DISCOUNTCARD_QUEUE, true, false, false, params);
    }

    @Bean
    public Queue payOrderDelayQueue() {

        return new Queue(QueueConstants.ORDER_CREATE_DELAY_QUEUE, true, false, false);
    }

    @Bean
    public Binding binding(TopicExchange payOrderExchange, Queue payOrderQueue) {

        return BindingBuilder.bind(payOrderQueue).to(payOrderExchange).with(RoutingKeyConstants.ORDER_CREATE_ROUTING_KEY);
    }

    @Bean
    public Binding discountCardBinding(TopicExchange payOrderExchange, Queue orderCreateDiscountCardQueue) {

        return BindingBuilder.bind(orderCreateDiscountCardQueue).to(payOrderExchange).with(RoutingKeyConstants.ORDER_CREATE_ROUTING_KEY);
    }

    @Bean
    public Binding payOrderDelayBinding(TopicExchange payOrderDelayExchange, Queue payOrderDelayQueue) {

        return BindingBuilder.bind(payOrderDelayQueue).to(payOrderDelayExchange).with(RoutingKeyConstants.ORDER_CREATE_ROUTING_KEY);
    }

    @Bean
    public PayOrderReceiver payOrderReceiver() {
        return new PayOrderReceiver();
    }

    @Bean
    public DsicountCardReceiver dsicountCardReceiver() {
        return new DsicountCardReceiver();
    }
}
