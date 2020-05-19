package com.limingliang.projects.rabbitmq.domain;

import com.limingliang.projects.rabbitmq.dictionary.MsgLogStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    public MsgLog(String msgId, String msg, String exchange, String routingKey) {
        this.msgId = msgId;
        this.msg = msg;
        this.exchange = exchange;
        this.routingKey = routingKey;

        this.status = MsgLogStatusEnum.DeliverIng.getCode();
        this.tryCount = 0;

        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        this.createTime = date;
        this.updateTime = date;
        this.nextTryTime = Date.from(localDateTime.plusMinutes(1L).atZone( ZoneId.systemDefault()).toInstant());
    }
}
