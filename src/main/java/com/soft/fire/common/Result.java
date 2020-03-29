/*
 * Copyright (c)
 */
package com.soft.fire.common;

/**
 * 统一响应体
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-28 17:25
 */
public class Result<T> {

    /**
     * 响应状态码 比如1000代表成功
     */
    private int code;
    /**
     * 响应信息 用来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;

    /**
     * 默认构造器
     *
     * @param data
     */
    public Result(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
