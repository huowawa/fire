/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft.fire.platform.emp.model.Emp;

import java.util.List;
import java.util.Map;

/**
 * 雇员业务接口
 *
 * @author David Lin
 * @version: 1.0
 * @date 2019-03-17 11:28
 */
public interface EmpService extends IService<Emp> {

    /**
     * 获取所有职员信息
     * @return
     */
    List<Emp> findAllEmp();
    /**
     * 根据编号获取员工信息
     * @param empno
     * @return
     */
    Emp getByEmpno(String empno);
}
