package com.limingliang.projects.rabbitmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/9 14:21
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@SpringBootApplication
@MapperScan("com.limingliang.projects.rabbitmq.payorder.mapper")
public class RabbitmqReceiverApplication {

    public static void main(String[] args) {

        SpringApplication.run(RabbitmqReceiverApplication.class, args);
    }
}
