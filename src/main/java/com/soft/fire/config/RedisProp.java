/*
 * Copyright (c)
 */
package com.soft.fire.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * redis配置对象
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-02-02 11:26
 */
@Component
@Setter
@Getter
public class RedisProp {
    /**
     * redis服务地址
     */
    private String host;
    /**
     * redis服务端口号
     */
    private int port;
    /**
     * redis最大连接数量
     */
    private int maxTotal;
    /**
     *
     */
    private int maxIdle;
}
