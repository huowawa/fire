/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.soft.fire.platform.emp.model.Emp;
import com.soft.fire.platform.emp.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
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

    @GetMapping("/saveemp")
    public Integer saveEmp(HttpServletRequest request) {
        Emp emp = new Emp();
        emp.setEmpname("fire2");
        emp.setJob("程序员");
        emp.setHiredate(new Timestamp(System.currentTimeMillis()));
        emp.setSal(20000);
        emp.setComm(40000);
        return empService.saveEmp(emp);
    }

    public Integer updateEmp(HttpServletRequest request) {
        return empService.updateEmp(null);
    }

    public Integer removeEmp(HttpServletRequest request) {
        return empService.removeEmp(null);
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request) {
        Emp emp = new Emp();
        emp.setHiredate(new Timestamp(System.currentTimeMillis()));
        emp.setSal(100);

        /**
         * 根据条件更新需要使用 条件构造器  QueryWrapper或者 UpdateWrapper
         */
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ename", "fire2");
        queryWrapper.ge("sal", 200);

        /**
         * 或者
         */
        UpdateWrapper<Emp> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("sal", 9999).set("hiredate", new Timestamp(System.currentTimeMillis()))
                .eq("ename", "fire2").ge("sal", 200);

        boolean result = empService.update(updateWrapper);



        logger.info("the exec result is {}", result + "");
        return "success";
    }

    @RequestMapping("/remove")
    public  String remove(HttpServletRequest request){
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ename","fire2");

        boolean result = empService.remove(queryWrapper);


        return "success"+result;
    }
}
