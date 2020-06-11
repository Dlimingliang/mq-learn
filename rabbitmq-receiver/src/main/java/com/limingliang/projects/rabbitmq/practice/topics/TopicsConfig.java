package com.limingliang.projects.rabbitmq.practice.topics;

import org.springframework.amqp.core.AnonymousQueue;
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
 * @Date: 2020/5/11 15:05
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Profile("topics")
@Configuration
public class TopicsConfig {

    @Bean
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1a(TopicExchange topicExchange, Queue autoDeleteQueue1) {

        return BindingBuilder.bind(autoDeleteQueue1).to(topicExchange).with("*.orange.*");
    }

    @Bean
    public Binding binding1b(TopicExchange topicExchange, Queue autoDeleteQueue1) {

        return BindingBuilder.bind(autoDeleteQueue1).to(topicExchange).with("*.*.rabbit");
    }

    @Bean
    public Binding binding2b(TopicExchange topicExchange, Queue autoDeleteQueue2) {

        return BindingBuilder.bind(autoDeleteQueue2).to(topicExchange).with("lazy.#");
    }

    @Bean
    public TopicsReceiver receiver() {
        return new TopicsReceiver();
    }
}
