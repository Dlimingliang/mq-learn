package com.limingliang.projects.rabbitmq.practice.pubsub;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/11 15:05
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Profile("pub_sub")
@Configuration
public class PubSubConfig {

    @Bean
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1(FanoutExchange fanoutExchange, Queue autoDeleteQueue1) {

        return BindingBuilder.bind(autoDeleteQueue1).to(fanoutExchange);
    }

    @Bean
    public Binding binding2(FanoutExchange fanoutExchange, Queue autoDeleteQueue2) {

        return BindingBuilder.bind(autoDeleteQueue2).to(fanoutExchange);
    }

    @Bean
    public PubSubReceiver receiver() {
        return new PubSubReceiver();
    }
}
