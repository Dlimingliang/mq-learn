package com.limingliang.projects.rocketmq.annotion.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: limingliang
 * @Description:
 * @Date: 2020/8/6 16:37
 * Copyright (c) 2017, zaodao All Rights Reserved.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DLLog {
}
