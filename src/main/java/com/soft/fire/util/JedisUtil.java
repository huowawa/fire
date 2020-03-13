/*
 * Copyright (c)
 */
package com.soft.fire.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * Jedis操作Redis工具类
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-02-06 15:44
 */
@Component
@Slf4j
public class JedisUtil {
    /**
     * 注入jedis连接池对象
     */
    @Resource
    private JedisPool jedisPool;

    /**
     * 获取Jedis连接对象，从连接池获取
     *
     * @return
     */
    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 获取string类型数据值
     *
     * @param key
     * @return
     */
    public String get(String key) {

        try (Jedis jedis = getJedis();) {
            return jedis.get(key);
        } catch (Exception e) {
            log.error("get key {} 异常了", key, e);
        }
        return null;
    }

    /**
     * 设置string类型值
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        try (Jedis jedis = getJedis();) {
            return jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key={},value={} 异常了", key, value, e);
        }
        return null;
    }

    /**
     * key自增1
     *
     * @param key
     * @return 自增1之后的值
     */
    public Long incr(String key) {
        try (Jedis jedis = getJedis();) {
            return jedis.incr(key);
        } catch (Exception e) {
            log.error("incr key={}异常了", key, e);
        }

        return null;
    }

    /**
     * @param key
     * @param increment
     * @return
     */
    public Long incrBy(String key, long increment) {
        try (
                Jedis jedis = getJedis();
        ) {
            return jedis.incrBy(key, increment);
        } catch (Exception e) {
            log.error("incrby key={},increment={}异常了", key, increment, e);
        }

        return null;
    }

    /**
     * string  类型数据自减1
     *
     * @param key
     * @return
     */
    public Long decr(String key) {
        try (
                Jedis jedis = getJedis();
        ) {
            return jedis.decr(key);
        } catch (Exception e) {
            log.error("decr key={}异常了", key, e);
        }
        return null;
    }

    /**
     * 设置string类型数据具有指定的生命周期
     *
     * @param key
     * @param seconds 时间(秒)
     * @param value
     * @return
     */
    public String setex(String key, int seconds, String value) {
        try (Jedis jedis = getJedis();) {
            return jedis.setex(key, seconds, value);
        } catch (Exception e) {
            log.error("setex key={},seconds={},value={}异常了", key, seconds, value, e);
        }
        return null;
    }

    /**
     * 设置string类型数据具有指定的生命周期
     *
     * @param key
     * @param milliseconds 时间(毫秒)
     * @param value
     * @return
     */
    public String psetex(String key, long milliseconds, String value) {
        try (
                Jedis jedis = getJedis();
        ) {
            return jedis.psetex(key, milliseconds, value);
        } catch (Exception e) {
            log.error("psetex key={},milliseconds={},value={}异常了", key, milliseconds, value, e);
        }
        return null;
    }

    /**
     * 删除指定的key
     *
     * @param key
     * @return
     */
    public boolean del(String key) {
        try (
                Jedis jedis = getJedis();
        ) {
            Long delResult = jedis.del(key);
            if (1L == delResult.longValue()) {
                return true;
            }
        } catch (Exception e) {
            log.error("del key={}异常了", key, e);
        }
        return false;
    }

    /**
     * 判断指定的key是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        try (Jedis jedis = getJedis();) {
            return jedis.exists(key);
        } catch (Exception e) {
            log.error("exists key={}异常了", key, e);
        }
        return false;
    }

    /**
     * 为指定key设置有效期
     *
     * @param key
     * @param seconds 时间(秒)
     * @return
     */
    public boolean expire(String key, int seconds) {
        try (Jedis jedis = getJedis();) {
            Long expireResult = jedis.expire(key, seconds);
            if (1L == expireResult.longValue()) {
                return true;
            }
        } catch (Exception e) {
            log.error("expire key={},seconds={}异常了", e);
        }
        return false;
    }

    /**
     * 为指定key设置有效期
     *
     * @param key
     * @param milliseconds 时间(毫秒)
     * @return
     */
    public boolean pexpire(String key, long milliseconds) {
        try (
                Jedis jedis = getJedis();
        ) {
            Long expireResult = jedis.pexpire(key, milliseconds);
            if (1L == expireResult.longValue()) {
                return true;
            }
        } catch (Exception e) {
            log.error("pexpire key={},milliseconds={}", key, milliseconds);
        }
        return false;
    }

    /**
     * 获取key的剩余有效时间
     *
     * @param key
     * @return 返回值单位(秒)
     */
    public Long ttl(String key) {
        try (Jedis jedis = getJedis();) {
            return jedis.ttl(key);
        } catch (Exception e) {
            log.error("ttl key={}异常了", key, e);
        }
        return null;
    }

    /**
     * 获取key的剩余有效时间
     *
     * @param key
     * @return 返回值单位(毫秒)
     */
    public Long pttl(String key) {
        try (Jedis jedis = getJedis();) {
            return jedis.pttl(key);
        } catch (Exception e) {
            log.error("ttl key={}异常了", key, e);
        }
        return null;
    }

}
