package com.limingliang.projects.rabbitmqsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/9 14:21
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@SpringBootApplication
@EnableScheduling
public class RabbitmqSenderApplication {

    public static void main(String[] args) {

        SpringApplication.run(RabbitmqSenderApplication.class, args);
    }
}
