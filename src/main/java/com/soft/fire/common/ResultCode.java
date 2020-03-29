package com.soft.fire.common;

/**
 * 响应码枚举
 */
public enum ResultCode {

    SUCCESS(1000, "操作成功!"),

    FAILED(1001, "操作失败!"),

    VALIDATE_FAILED(1002, "参数效验失败!"),

    ERROR(5000, "服务器出错，请联系管理员!");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
