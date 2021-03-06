package com.limingliang.projects.rocketmq.mapper;

import com.limingliang.projects.rocketmq.domain.PayOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/14 16:33
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Mapper
public interface PayOrderMapper {

    PayOrder getByOrderCode(String orderCode);

    int create(PayOrder payOrder);

    int update(PayOrder payOrder);
}
