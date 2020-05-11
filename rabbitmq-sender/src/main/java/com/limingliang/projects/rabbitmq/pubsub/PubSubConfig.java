package com.limingliang.projects.rabbitmq.pubsub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/11 15:01
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Profile("pub_sub")
@Configuration
public class PubSubConfig {

    @Bean
    public PubSubSender sender() {
        return new PubSubSender();
    }
}
