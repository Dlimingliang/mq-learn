package com.limingliang.projects.rocketmq.dictionary;

import java.io.Serializable;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/20 14:36
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */
public enum OrderStatusEnum implements Serializable {

    NO_PAY(1, "待支付"), PayAlready(2, "已支付");

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

    OrderStatusEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }
}
