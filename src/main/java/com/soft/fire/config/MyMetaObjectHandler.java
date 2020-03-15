/*
 * Copyright (c)
 */
package com.soft.fire.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Timestamp;

/**
 * 自定义填充器
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-15 17:28
 */
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入时填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("createTime", metaObject);
        //如果为空 才进行填充
        if (null == createTime) {
            this.strictInsertFill(metaObject, "createTime", Timestamp.class, new Timestamp(System.currentTimeMillis()));
        }
    }

    /**
     * 更新时填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("updateTime", metaObject);
        //如果为空 才进行填充
        if (null == createTime) {
            this.strictUpdateFill(metaObject, "updateTime", Timestamp.class, new Timestamp(System.currentTimeMillis()));
        }
    }
}
