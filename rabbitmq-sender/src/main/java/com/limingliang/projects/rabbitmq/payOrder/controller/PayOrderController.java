package com.limingliang.projects.rabbitmq.payOrder.controller;

import com.alibaba.fastjson.JSON;
import com.limingliang.projects.rabbitmq.dictionary.OrderStatusEnum;
import com.limingliang.projects.rabbitmq.domain.PayOrder;
import com.limingliang.projects.rabbitmq.payOrder.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/14 16:23
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@RestController
@RequestMapping(value = "payOrders")
public class PayOrderController {

    @Autowired
    private PayOrderService payOrderService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String orderCreate() {

        Date now = new Date();
        PayOrder payOrder = PayOrder.builder()
                .orderCode(String.valueOf(now.getTime()))
                .goodsId(1L)
                .goodsName("macPro")
                .orginPrice(new BigDecimal(new Random().nextInt(100)))
                .salePrice(new BigDecimal(new Random().nextInt(10)))
                .status(OrderStatusEnum.NO_PAY.getCode())
                .userId(4486L)
                .userName("lalala")
                .createTime(now)
                .build();
        payOrderService.create(payOrder);

        return JSON.toJSONString(payOrder);
    }
}
