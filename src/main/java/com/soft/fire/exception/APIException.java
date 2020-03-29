/*
 * Copyright (c)
 */
package com.soft.fire.exception;

/**
 * 自定义异常
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-28 17:16
 */
public class APIException extends RuntimeException {

    /**
     * 异常状态码
     */
    private int code;
    /**
     * 异常信息
     */
    private String msg;

    /**
     * 默认构造器
     */
    public APIException() {
        this(10001, "后台发生错误，请联系管理员");
    }

    public APIException(String msg) {
        this(10001, msg);
    }

    public APIException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
