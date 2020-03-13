/*
 * Copyright (c)
 */
package com.soft.fire.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Oracle数据库相关配置
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-13 22:49
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
@Setter
@Getter
public class OracleProperties {
    /**
     * 驱动类名称
     */
    private String driverClassName;
    /**
     * 数据库地址
     */
    private String url;
    /**
     * 数据库账号
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;

}
