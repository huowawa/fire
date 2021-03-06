/*
 * Copyright (c)
 */
package com.soft.fire.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.soft.fire.utils.PlatStringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义ID生成器
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-02-22 23:11
 */
@Slf4j
public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Number nextId(Object entity) {
        return null;
    }

    @Override
    public String nextUUID(Object entity) {
        log.info("调用了UUID 生成!");
        return PlatStringUtil.randomUUID();
    }

}
