/*
 * Copyright (c)
 */
package com.soft.fire.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-02-22 23:15
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 注入 自定义填充器
     * @return
     */
    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MyMetaObjectHandler();
    }


    /**
     * 注册分页查询
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    /**
     * 注册UUID生成器
     * @return
     */
    @Bean
    public IdentifierGenerator idGenerator() {
        //自定义ID生成器
        return new CustomIdGenerator();
    }

    /**
     * Oracle序列生成器
     * @return
     */
    @Bean
    public IKeyGenerator keyGenerator(){
        return new OracleKeyGenerator();
    }


}
