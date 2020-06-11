package com.limingliang.projects.rabbitmq.configuration;

import com.limingliang.projects.rabbitmq.constants.ExchangeConstants;
import com.limingliang.projects.rabbitmq.constants.QueueConstants;
import com.limingliang.projects.rabbitmq.constants.RoutingKeyConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/6/10 16:33
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Configuration
public class DeadLetterConfiguration {

    @Bean
    public DirectExchange deadLetterExchange() {

        return new DirectExchange(ExchangeConstants.COMMON_DEAD_LETTER_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Queue deadLetterQueue() {

        return new Queue(QueueConstants.COMMON_DEAD_LETTER_QUEUE, true, false, false);
    }

    @Bean
    public Binding deadLetterBinding() {

        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange())
                .with(RoutingKeyConstants.COMMON_DEAD_LETTER_ROUTING_KEY);
    }
}
