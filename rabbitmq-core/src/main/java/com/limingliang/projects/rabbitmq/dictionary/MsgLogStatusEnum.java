package com.limingliang.projects.rabbitmq.dictionary;

import java.io.Serializable;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/16 17:16
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */
public enum MsgLogStatusEnum implements Serializable {

    DeliverIng(1, "投递中"), DeliverSuccess(2, "投递成功"),
    DeliverFail(3, "投递失败"), NotDeliver(4, "不在投递"),
    ConsumerSuccess(5, "消费成功");

    private int code;

    private String label;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    MsgLogStatusEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }
}
