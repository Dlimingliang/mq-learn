package com.limingliang.projects.rabbitmqsender.helloworld;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/9 15:45
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Profile({"hello_world"})
@Configuration
public class HelloWorldConfig {

    @Bean
    public Queue hello(){
        return new Queue("hello_world");
    }

    @Bean
    public HelloWorldSender sender() {
        return new HelloWorldSender();
    }
}
