package com.limingliang.projects.rabbitmq.exchange;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/11 14:58
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Profile("routing")
@Configuration
public class RoutingExchange {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("routing_direct");
    }
}