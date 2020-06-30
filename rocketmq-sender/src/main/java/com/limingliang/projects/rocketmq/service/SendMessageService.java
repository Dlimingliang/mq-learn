package com.limingliang.projects.rocketmq.service;

import com.limingliang.projects.rocketmq.constants.TopicConstants;
import com.limingliang.projects.rocketmq.domain.PayOrder;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/6/11 11:52
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Service
public class SendMessageService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private PayOrderService payOrderService;

    @Transactional
    public void sendMessage(PayOrder payOrder) {

        payOrderService.create(payOrder);
        rocketMQTemplate.convertAndSend(TopicConstants.OrderTopic, payOrder);
    }
}
