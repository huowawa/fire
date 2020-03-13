/*
 * Copyright (c)
 */
package com.soft.fire.config;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
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

    @Bean
    public IdentifierGenerator idGenerator() {
        //自定义ID生成器
        return new CustomIdGenerator();
    }

    @Bean
    public IKeyGenerator keyGenerator(){
        return new OracleKeyGenerator();
    }
}
