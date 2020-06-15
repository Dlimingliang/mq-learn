package com.limingliang.projects.rocketmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/9 14:21
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@RestController
@SpringBootApplication
@MapperScan(basePackages = "com.limingliang.projects.rocketmq.mapper")
public class RocketMqSenderApplication {

    @RequestMapping(value = "/")
    public String index() {
        return "RocketMqSender 启动完成";
    }

    public static void main(String[] args) {

        SpringApplication.run(RocketMqSenderApplication.class, args);
    }

}
