package com.limingliang.projects.rabbitmq;

import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan(basePackages = "com.limingliang.projects.rabbitmq.mapper")
public class RabbitmqSenderApplication {

    public static void main(String[] args) {

        SpringApplication.run(RabbitmqSenderApplication.class, args);
    }
}
