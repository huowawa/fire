/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.controller;

import com.soft.fire.platform.emp.model.Emp;
import com.soft.fire.platform.emp.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 雇员控制器
 *
 * @author David Lin
 * @version: 1.0
 * @date 2019-03-17 11:29
 */
@RestController
public class EmpController {

    /**
     * 日志操作对象
     */
    Logger logger = LoggerFactory.getLogger(EmpController.class);
    /**
     * 员工业务操作对象
     */
    @Resource
    private EmpService empService;

    @GetMapping("/empall")
    public List<Emp> findAll(HttpServletRequest request) {
        return empService.list(null);
    }

    @GetMapping("/emp")
    public Emp getEmp(HttpServletRequest request) {
        return empService.getByEmpno("592");
    }
}
