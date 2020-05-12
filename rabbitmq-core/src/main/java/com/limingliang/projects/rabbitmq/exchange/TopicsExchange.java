package com.limingliang.projects.rabbitmq.exchange;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/12 08:57
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Profile("topics")
@Configuration
public class TopicsExchange {

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topics_topic");
    }
}
