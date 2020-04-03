/*
 * Copyright (c)
 */
package com.soft.fire.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.soft.fire.common.MyPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分页工具
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-14 23:35
 */
public class Condition {

    /**
     * 转化成mybatis plus中的Page
     *
     * @return IPage
     */
    public static <T> MyPage<T> getPage(long current, long size, Map<String, String> orderParams) {
        MyPage<T> myPage = new MyPage(current, size);
        if (null == orderParams||orderParams.isEmpty()) {
            return myPage;
        }
        List<OrderItem> orderItems = new ArrayList<>();
        orderParams.forEach((k, v) -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setColumn(k);
            if(v.toUpperCase().equals("ASC")){
                orderItem.setAsc(true);
            }else{
                orderItem.setAsc(false);
            }

            orderItems.add(orderItem);
        });
        myPage.setOrders(orderItems);
        return myPage;
    }

    /**
     * 获取mybatis plus中的QueryWrapper
     *
     * @param entity 实体
     * @param <T>    类型
     * @return QueryWrapper
     */
    public static <T> QueryWrapper<T> getQueryWrapper(T entity) {
        return new QueryWrapper<>(entity);
    }

    /**
     * 获取mybatis plus中的QueryWrapper
     *
     * @param query 查询条件
     * @param clazz 实体类
     * @param <T>   类型
     * @return QueryWrapper
     */
    public static <T> QueryWrapper<T> getQueryWrapper(Map<String, Object> query, Class<T> clazz) {

        return getQueryWrapper(query, null, clazz);
    }

    /**
     * 获取mybatis plus中的QueryWrapper
     *
     * @param query   查询条件
     * @param exclude 排除的查询条件
     * @param clazz   实体类
     * @param <T>     类型
     * @return QueryWrapper
     */
    public static <T> QueryWrapper<T> getQueryWrapper(Map<String, Object> query, Map<String, Object> exclude, Class<T> clazz) {
        exclude.forEach((k, v) -> query.remove(k));
        QueryWrapper<T> qw = new QueryWrapper<>();
        //qw.setEntity(BeanUtil.newInstance(clazz));
        SqlKeyword.buildCondition(query, qw);

        return qw;
    }
}
