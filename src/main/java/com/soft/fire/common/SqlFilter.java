/*
 * Copyright (c)
 */
package com.soft.fire.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft.fire.util.Condition;
import com.soft.fire.util.PlatStringUtil;
import com.soft.fire.util.SqlKeyword;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 前端参数封装
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-14 22:55
 */
public class SqlFilter<T> {
    /**
     * 过滤类型查询参数
     */
    public static final int FILTER_TYPE_QUERY = 1;
    /**
     * 过滤类型:排序参数
     */
    public static final int FILTER_TYPE_ORDER = 2;
    /**
     * 过滤类型:分组参数
     */
    public static final int FILTER_TYPE_GROUP = 3;
    /**
     * 属性null
     */
    private HttpServletRequest request = null;
    /**
     * 分页工具
     */
    private MyPage<T> myPage = null;
    /**
     * 当前页面 第1页
     */
    private Long current = 1L;
    /**
     * 每页显示的条数
     */
    private Long size = 10L;
    /**
     * 查询参数
     */
    private QueryWrapper<?> queryWrapper = new QueryWrapper<>();

    /**
     * 查询参数
     */
    private Map<String, Object> queryParams = new HashMap<String, Object>();
    /**
     * 排序参数
     */
    private Map<String, String> orderParams = new LinkedHashMap<String, String>();
    /**
     * 分组排序参数
     */
    private Map<String, String> groupParams = new LinkedHashMap<String, String>();


    /**
     * 添加过滤参数到filter
     *
     * @param paramName:参数名称
     * @param paramValue:参数值
     * @param type           过滤类型 1:查询 2:排序 3:分组
     */
    public void addFilter(String paramName, String paramValue, int type) {
        String[] fieldInfo = paramName.split("[_]");
        try {
            if (fieldInfo != null && fieldInfo.length > 0) {
                switch (type) {
                    case SqlFilter.FILTER_TYPE_QUERY:
                        queryParams.put(paramName, paramValue);
                        break;
                    case SqlFilter.FILTER_TYPE_ORDER:
                        orderParams.put(paramName, paramValue);
                        break;
                    case SqlFilter.FILTER_TYPE_GROUP:
                        groupParams.put(paramName, paramValue);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通用查询对象
     * 1、参数以Q_打头的将会纳入到查询条件中
     * 2、参数以O_打头的将会作为排序条件
     * 3、参数以G_打头的将会作为分组条件
     *
     * @param sqlParams
     */
    public SqlFilter(Map<String, Object> sqlParams) {
        constructFilter(sqlParams);
    }

    /**
     * 构造各种参数
     *
     * @param sqlParams
     */
    private void constructFilter(Map<String, Object> sqlParams) {
        Iterator it = sqlParams.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            String fieldName = entry.getKey();
            Object val = entry.getValue();
            if (val != null && PlatStringUtil.isNotBlank(val.toString())) {
                if (fieldName.startsWith("Q_")) {
                    //获取最后一个操作符
                    this.addFilter(fieldName, val.toString().trim(), 1);
                } else if (fieldName.startsWith("O_")) {
                    this.addFilter(fieldName, val.toString().trim(), 2);
                } else if (fieldName.startsWith("G_")) {
                    this.addFilter(fieldName, val.toString().trim(), 3);
                }
            }
        }
        /**
         * 构造完成之后 生成分页对象  和查询封装
         */
        // 获取当前页
        String currentPage = (String) sqlParams.get("current");
        String s_limit = (String) sqlParams.get("limit");
        if (PlatStringUtil.isNotBlank(s_limit)&& PlatStringUtil.isNotBlank(currentPage)) {
            this.current = Long.valueOf(currentPage);
            this.size = Long.valueOf(s_limit);

        }

       // this.myPage = Condition.getPage(this.current, this.size,this.orderParams);

        //SqlKeyword.buildCondition(this.queryParams,this.queryWrapper);
        //this.qw = Condition.getQueryWrapper(this.queryParams,null,null);

    }

    /**
     * 通用查询对象
     * 1、参数以Q_打头的将会纳入到查询条件中
     * 2、参数以O_打头的将会作为排序条件
     * 3、参数以G_打头的将会作为分组条件
     *
     * @param request
     */
    public SqlFilter(HttpServletRequest request) {
        this.request = request;
        Map<String, Object> sqlParams = getMapFromRequest(request);
        this.constructFilter(sqlParams);
    }

    /**
     * 获取requst请求参数的所有值
     *
     * @param request
     * @return
     */
    public Map<String, Object> getMapFromRequest(
            HttpServletRequest request) {
        Map reqMap = request.getParameterMap();
        HashMap<String, Object> datas = new HashMap<String, Object>();
        Iterator it = reqMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            String[] val = (String[]) entry.getValue();
            if (val.length == 1) {
                if (PlatStringUtil.isNotBlank(val[0])) {
                    datas.put(key, val[0]);
                } else {
                    datas.put(key, "");
                }
            } else {
                if (val != null) {
                    datas.put(key, val);
                } else {
                    datas.put(key, "");
                }
            }
        }
        return datas;
    }

    /**
     * 获取分页对象
     * @return
     */
    public MyPage<T> getMyPage() {
        this.myPage = Condition.getPage(this.current, this.size,this.orderParams);
        return myPage;
    }

    /**
     * 获取查询条件包装器
     * @return
     */
    public QueryWrapper<?> getQueryWrapper() {
        SqlKeyword.buildCondition(this.queryParams,this.queryWrapper);
        return queryWrapper;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }
}
