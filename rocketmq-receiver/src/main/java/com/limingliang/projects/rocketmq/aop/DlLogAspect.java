package com.limingliang.projects.rocketmq.aop;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/8/7 10:21
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Slf4j
@Aspect
@Component
public class DlLogAspect {

    @Pointcut("execution(* org.apache.rocketmq.spring.core.RocketMQListener.onMessage(..)) && args(messageExt)")
    public void dlMessageLog(MessageExt messageExt) {}


    @After("dlMessageLog(messageExt)")
    public void validateAccount(MessageExt messageExt) {

        log.info("拦截到消息" + JSON.toJSONString(messageExt));

        
    }
}
