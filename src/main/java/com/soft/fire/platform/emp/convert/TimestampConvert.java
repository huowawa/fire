/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.convert;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * 自定义时间转换器
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-04-12 17:24
 */
@Component
public class TimestampConvert {

    public String timeStampToString(Timestamp updateTime){
        return updateTime.toString();
    }
}
