/*
 * Copyright (c)
 */
package com.soft.fire.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用的控制器
 *
 * @author David Lin
 * @version: 1.0
 * @date 2019-02-16 20:29
 */
@RestController
@CrossOrigin
public class CommonController {
    /**
     * 日志操作对象
     */
    Logger logger = LoggerFactory.getLogger(CommonController.class);


    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    @GetMapping("/user")
    public Map getUser(HttpServletRequest request) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("userName", "smith");
        userMap.put("age", 25);
        userMap.put("mobilePhone", "18046056452");
        logger.info("hello");

        return userMap;
    }

    /**
     * 模拟验证用户名是否存在
     *
     * @param request
     * @return
     */
    @GetMapping("/api/vusername")
    public Map validateUserName(HttpServletRequest request) {
        String username = request.getParameter("username");

        Map result = new HashMap();
        result.put("data",username);
        result.put("exists", true);
        return result;
    }

    /**
     * 模拟验证手机号码是否已经存在
     *
     * @param request
     * @return
     */
    @GetMapping("/api/vphone")
    public Map validatePhone(HttpServletRequest request) {
        String phone = request.getParameter("phone");

        Map result = new HashMap();
        result.put("data",phone);
        result.put("exists", true);
         //int num = 10/0;
        return result;
    }

    /**
     * 模拟保存
     *
     * @param request
     * @return
     */
    @GetMapping("/api/save")
    public Map save(HttpServletRequest request) {
        Map result = new HashMap();
        result.put("msg", "保存成功");
        return result;
    }
}
