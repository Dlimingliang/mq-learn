package com.limingliang.projects.rocketmq.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/14 16:16
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayOrder implements Serializable {

    private static final long serialVersionUID = -4644359370387357218L;

    private String orderCode;

    private Long goodsId;

    private String goodsName;

    private BigDecimal orginPrice;

    private BigDecimal salePrice;

    private Integer status;

    private Long userId;

    private String userName;

    private Date createTime;
}
