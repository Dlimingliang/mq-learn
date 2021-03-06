package com.limingliang.projects.rabbitmq.exchange;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/11 14:58
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Configuration
public class PubSubExchange {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("pub_sub_fanout");
    }
}
