package com.limingliang.projects.rabbitmq.payorder.service;

import com.limingliang.projects.rabbitmq.domain.MsgLog;
import com.limingliang.projects.rabbitmq.payorder.mapper.MsgLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/5/14 16:33
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Service
@Transactional(readOnly = true)
public class MsgLogService {

    @Autowired
    private MsgLogMapper msgLogMapper;

    public MsgLog getByMsgId(String msgId) {
        return msgLogMapper.getByMsgId(msgId);
    }

    @Transactional
    public boolean create(MsgLog msgLog) {
        return msgLogMapper.create(msgLog) > 0;
    }

    @Transactional
    public boolean update(MsgLog msgLog) {
        return msgLogMapper.update(msgLog) > 0;
    }
}
