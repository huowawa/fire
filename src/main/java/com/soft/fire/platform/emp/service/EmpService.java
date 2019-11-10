/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.service;

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
public interface EmpService {

    /**
     * 根据名称 获取雇员信息
     * @param ename
     * @return
     */
     Map<String,Object> getEmpByName(String ename) ;

    /**
     * 根据编号获取员工信息
     * @param empno
     * @return
     */
     Emp getEmpByNo(String empno);

    /**
     * 获取任务
     * @param exeId
     * @return
     */
    List<Map<String,Object>> findTaskByExeId(String exeId);

    /**
     * 获取所有职员信息
     * @return
     */
    List<Emp> findAllEmp();

    /**
     * 根据编号删除雇员
     * @param empno
     */
    void removeEmp(Integer empno);

    int saveEmp(Emp emp);
}
