/*
 * Copyright (c)
 */
package com.soft.fire.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis连接池配置
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-02-06 15:30
 */
@Configuration
public class RedisConfig {

    /**
     * 注册jedis连接池对象
     * @return
     */
    @Bean
    public JedisPool jedisPool(){
        RedisProp redisProperties = redisProperties();
        //连接池配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMaxTotal(redisProperties.getMaxTotal());
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,redisProperties.getHost(),redisProperties.getPort());
        return jedisPool;
    }

    /**
     * 注册redis参数对象
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "redis" )
    public RedisProp redisProperties(){
        return new RedisProp();
    }
}
