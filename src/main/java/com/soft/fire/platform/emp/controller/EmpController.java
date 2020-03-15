/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.soft.fire.common.SqlFilter;
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
        emp.setSal(26000);

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
        updateWrapper.eq("ename", "fire2");

        boolean result = empService.update(emp,updateWrapper);


        logger.info("the exec result is {}", result + "");
        return "success";
    }

    @RequestMapping("/remove")
    public String remove(HttpServletRequest request) {
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ename", "fire2");

        boolean result = empService.remove(queryWrapper);


        return "success" + result;
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @RequestMapping("/pages")
    public IPage<Emp> selectPage(HttpServletRequest request) {
        SqlFilter<Emp> sqlFilter = new SqlFilter<Emp>(request);
        sqlFilter.addFilter("e.empno", "asc", SqlFilter.FILTER_TYPE_ORDER);

        IPage<Emp> empIPage = empService.findBySqlFilter(sqlFilter);


        logger.info("数据总条数:" + empIPage.getTotal());
        logger.info("数据总页数：" + empIPage.getPages());

        return empIPage;
    }

    @RequestMapping("list")
    public List<Emp> listEmp(HttpServletRequest request) {

        QueryWrapper<Emp> queryWrapper = new QueryWrapper();
        queryWrapper.eq("deptno",20);
        queryWrapper.select("empno,ename,job,deptno");

        Emp emp = new Emp();

        return emp.selectList(queryWrapper);
    }

}
