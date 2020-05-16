package com.limingliang.projects.rabbitmq.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/16 16:52
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MsgLog implements Serializable {

    private String msgId;

    private String msg;

    private String exchange;

    private String routingKey;

    //状态: 0投递中 1投递成功 2投递失败 3已消费
    private Integer status;

    //重试次数
    private Integer tryCount;

    //下次重试时间
    private Date nextTryTime;

    private Date createTime;

    private Date updateTime;
}
