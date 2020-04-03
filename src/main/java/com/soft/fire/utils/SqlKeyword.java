/*
 * Copyright (c)
 */
package com.soft.fire.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;

/**
 * Mp查询条件生成
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-14 22:30
 */
public class SqlKeyword {
    private static final String EQUAL = "_equal";
    private static final String NOT_EQUAL = "_notequal";
    private static final String LIKE = "_like";
    private static final String NOT_LIKE = "_notlike";
    private static final String GT = "_gt";
    private static final String LT = "_lt";
    private static final String DATE_GT = "_dategt";
    private static final String DATE_EQUAL = "_dateequal";
    private static final String DATE_LT = "_datelt";
    private static final String IS_NULL = "_null";
    private static final String NOT_NULL = "_notnull";
    private static final String IGNORE = "_ignore";

    /**
     * 条件构造器
     *
     * @param query 查询字段
     * @param qw    查询包装类
     */
    public static void buildCondition(Map<String, Object> query, QueryWrapper<?> qw) {
        if (null == query||query.isEmpty()) {
            return;
        }
        query.forEach((k, v) -> {
            if (PlatStringUtil.isBlank(k)
                    || null == v
                    || k.endsWith(IGNORE)) {
                return;
            }
            if (k.endsWith(EQUAL)) {
                qw.eq(getColumn(k, EQUAL), v);
            } else if (k.endsWith(NOT_EQUAL)) {
                qw.ne(getColumn(k, NOT_EQUAL), v);
            } else if (k.endsWith(NOT_LIKE)) {
                qw.notLike(getColumn(k, NOT_LIKE), v);
            } else if (k.endsWith(GT)) {
                qw.gt(getColumn(k, GT), v);
            } else if (k.endsWith(LT)) {
                qw.lt(getColumn(k, LT), v);
            } else if (k.endsWith(DATE_GT)) {
                qw.gt(getColumn(k, DATE_GT), v);
            } else if (k.endsWith(DATE_EQUAL)) {
                qw.eq(getColumn(k, DATE_EQUAL), v);
            } else if (k.endsWith(DATE_LT)) {
                qw.lt(getColumn(k, DATE_LT), v);
            } else if (k.endsWith(IS_NULL)) {
                qw.isNull(getColumn(k, IS_NULL));
            } else if (k.endsWith(NOT_NULL)) {
                qw.isNotNull(getColumn(k, NOT_NULL));
            } else {
                qw.like(getColumn(k, LIKE), v);
            }
        });
    }

    /**
     * 获取数据库字段, 去除后缀
     *
     * @param column  字段名
     * @param keyword 关键字
     * @return String
     */
    private static String getColumn(String column, String keyword) {
       return  column.substring(column.indexOf("_") + 1,
                column.lastIndexOf("_"));
        //return StringUtil.humpToUnderline(StringUtil.removeSuffix(column, keyword));

    }

}
