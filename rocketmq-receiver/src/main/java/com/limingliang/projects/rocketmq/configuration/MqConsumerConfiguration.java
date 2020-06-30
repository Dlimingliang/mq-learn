package com.limingliang.projects.rocketmq.configuration;

import com.limingliang.projects.rocketmq.receiver.DiscountCardReceiver;
import com.limingliang.projects.rocketmq.receiver.PayOrderReceiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/6/30 16:27
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Configuration
public class MqConsumerConfiguration {

    @Bean
    public PayOrderReceiver payOrderReceiver() {
        return new PayOrderReceiver();
    }

    @Bean
    public DiscountCardReceiver discountCardReceiver() {
        return new DiscountCardReceiver();
    }
}
