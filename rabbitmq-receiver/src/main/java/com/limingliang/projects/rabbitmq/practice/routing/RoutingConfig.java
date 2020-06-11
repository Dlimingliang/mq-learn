package com.limingliang.projects.rabbitmq.practice.routing;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
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

@Profile("routing")
@Configuration
public class RoutingConfig {

    @Bean
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1a(DirectExchange directExchange, Queue autoDeleteQueue1) {

        return BindingBuilder.bind(autoDeleteQueue1).to(directExchange).with("orange");
    }

    @Bean
    public Binding binding1b(DirectExchange directExchange, Queue autoDeleteQueue1) {

        return BindingBuilder.bind(autoDeleteQueue1).to(directExchange).with("black");
    }

    @Bean
    public Binding binding2a(DirectExchange directExchange, Queue autoDeleteQueue2) {

        return BindingBuilder.bind(autoDeleteQueue2).to(directExchange).with("green");
    }

    @Bean
    public Binding binding2b(DirectExchange directExchange, Queue autoDeleteQueue2) {

        return BindingBuilder.bind(autoDeleteQueue2).to(directExchange).with("black");
    }

    @Bean
    public RoutingReceiver receiver() {
        return new RoutingReceiver();
    }
}
