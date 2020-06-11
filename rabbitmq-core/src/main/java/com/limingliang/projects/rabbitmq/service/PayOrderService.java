package com.limingliang.projects.rabbitmq.service;

import com.limingliang.projects.rabbitmq.domain.PayOrder;
import com.limingliang.projects.rabbitmq.mapper.PayOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/14 16:40
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Service
@Transactional(readOnly = true)
public class PayOrderService {

    @Autowired
    private PayOrderMapper payOrderMapper;

    public PayOrder getByOrderCode(String orderCode) {
        return payOrderMapper.getByOrderCode(orderCode);
    }

    @Transactional
    public boolean create(PayOrder payOrder){

        boolean result = payOrderMapper.create(payOrder) > 0;
        return result ;
    }

    @Transactional
    public boolean update(PayOrder payOrder) {
        return payOrderMapper.update(payOrder) > 0;
    }
}
