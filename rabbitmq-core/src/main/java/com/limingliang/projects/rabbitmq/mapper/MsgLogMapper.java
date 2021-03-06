package com.limingliang.projects.rabbitmq.mapper;

import com.limingliang.projects.rabbitmq.domain.MsgLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/14 16:33
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Mapper
public interface MsgLogMapper {

    List<MsgLog> selectTimeoutMsg();

    MsgLog getByMsgId(String msgId);

    int create(MsgLog msgLog);

    int update(MsgLog msgLog);
}
