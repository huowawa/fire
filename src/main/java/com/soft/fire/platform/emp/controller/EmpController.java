/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.controller;

import com.soft.fire.platform.emp.model.Emp;
import com.soft.fire.platform.emp.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 雇员控制器
 *
 * @author David Lin
 * @version: 1.0
 * @date 2019-03-17 11:29
 */
@RestController
@CrossOrigin
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

    @RequestMapping("/empname")
    public Map<String, Object> getEmp() {
        return empService.getEmpByName("SMITH");
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping("/empNo")
    public Emp getEmpByNo(HttpServletRequest request) {
        //try {
        //    Thread.sleep(5000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        String empno = request.getParameter("empno");
        return empService.getEmpByNo(empno);
    }

    @RequestMapping("/empno")
    public Emp getEmpByNo() {
        return empService.getEmpByNo("7369");
    }

    @RequestMapping("/task")
    public List<Map<String, Object>> findTaskByExeId() {
        List<Map<String, Object>> list = empService.findTaskByExeId("FJFDA16072103477");

        return empService.findTaskByExeId("FJFDA16072103477");
    }

    @GetMapping("/emps")
    public List<Emp> findAllEmp() {
        return empService.findAllEmp();
    }


    @GetMapping(path = "/{empno}")
    public Emp getEmp(@PathVariable String empno) {
        return empService.getEmpByNo(empno);
    }

    @GetMapping("/")
    public Map<String, Object> getEmpByName(@RequestParam String ename) {
        return empService.getEmpByName(ename);
    }

    @DeleteMapping("/")
    public Map<String, Object> removeEmp(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String empno = request.getParameter("empno");
        empService.removeEmp(Integer.valueOf(empno));
        result.put("status", "0");
        result.put("message", "删除成功");

        return result;
    }

    /**
     * 保存员工信息
     *
     * @param request
     * @return
     */
    @PostMapping("/")
    public Map<String, Object> saveEmp(HttpServletRequest request, @RequestBody Emp emp) {

        Map<String, Object> result = new HashMap<>(8);
        empService.saveEmp(emp);
        result.put("status", "0");
        result.put("message", "保存成功");
        return result;
    }

    @PostMapping("/emp")
    public Map<String, Object> saveEmp(HttpServletRequest request) {

        String empno = request.getParameter("empno");
        String ename = request.getParameter("ename");
        String job = request.getParameter("job");


        Emp emp = new Emp();
        emp.setJob(job);
        emp.setEname(ename);
        emp.setEmpno(Integer.valueOf(empno));

        empService.saveEmp(emp);

        Map<String, Object> result = new HashMap<>(8);
        result.put("status", "0");
        result.put("message", "保存成功");
        return result;
    }


}
