package com.limingliang.projects.rabbitmq.exchange;

import com.limingliang.projects.rabbitmq.constants.ExchangeConstants;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/12 08:57
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Configuration
public class TopicsExchange {

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topics_topic");
    }

    // durable 是否持久化
    // 声明一个持久化不自动删除的队交换器
    @Bean
    public TopicExchange payOrderExchange() {
        return new TopicExchange(ExchangeConstants.ORDER_TOPIC_EXCHANGE, true, false);
    }

    @Bean
    public TopicExchange payOrderDelayExchange() {

        return ExchangeBuilder.topicExchange(ExchangeConstants.ORDER_TOPIC_DELAY_EXHCANGE)
                .durable(true)
                .delayed()
                .build();
    }
}
