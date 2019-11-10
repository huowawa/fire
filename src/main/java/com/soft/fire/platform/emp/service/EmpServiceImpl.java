/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.service;

import com.soft.fire.platform.emp.dao.EmpDao;
import com.soft.fire.platform.emp.model.Emp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 雇员业务操作实现类
 *
 * @author David Lin
 * @version: 1.0
 * @date 2019-03-17 11:28
 */
@Service("empService")
public class EmpServiceImpl implements  EmpService {
    /**
     * 注入dao依赖
     */
    @Resource
    private EmpDao empDao;

    /**
     * 根据名称 获取雇员信息
     *
     * @param ename
     * @return
     */
    @Override
    public Map<String,Object> getEmpByName(String ename)  {
      return empDao.getEmpByName(ename);
    }

    @Override
    public Emp getEmpByNo(String empno) {
        return empDao.getEmpByNo(empno);
    }

    /**
     * 获取任务
     *
     * @param exeId
     * @return
     */
    @Override
    public List<Map<String, Object>> findTaskByExeId(String exeId) {
        return empDao.findTaskByExeId(exeId);
    }

    /**
     * 获取所有职员信息
     *
     * @return
     */
    @Override
    public List<Emp> findAllEmp() {
        return empDao.findAllEmp();
    }

    @Override
  //  @Transactional
    public void removeEmp(Integer empno) {
      empDao.removeEmp(empno);
    }

    //@Transactional
    @Override
    public int saveEmp(Emp emp) {
        return empDao.saveEmp(emp);
    }

}
