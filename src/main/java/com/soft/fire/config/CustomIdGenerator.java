/*
 * Copyright (c)
 */
package com.soft.fire.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 自定义ID生成器
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-02-22 23:11
 */
@Slf4j
public class CustomIdGenerator implements IdentifierGenerator {

    private final AtomicLong al = new AtomicLong(1);

    @Override
    public Number nextId(Object entity) {
        //可以将当前传入的class全类名来作为bizKey,或者提取参数来生成bizKey进行分布式Id调用生成.
        String bizKey = entity.getClass().getName();
        MetaObject metaObject = SystemMetaObject.forObject(entity);
        String empno = (String) metaObject.getValue("empno");
        final long id = al.getAndIncrement();
        log.info("为{}生成主键值->:{}", empno, id);
        return id;
    }

    @Override
    public String nextUUID(Object entity) {
        return null;
    }

}
