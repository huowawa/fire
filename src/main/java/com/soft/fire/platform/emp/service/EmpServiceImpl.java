/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft.fire.platform.emp.mapper.EmpMapper;
import com.soft.fire.platform.emp.model.Emp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 雇员业务操作实现类
 *
 * @author David Lin
 * @version: 1.0
 * @date 2019-03-17 11:28
 */
@Service("empService")
public class EmpServiceImpl extends ServiceImpl<EmpMapper,Emp> implements  EmpService {
    /**
     * 注入dao依赖
     */
    @Resource
    private EmpMapper empMapper;

    @Override
    public List<Emp> findAllEmp() {
        List<Emp> empList = empMapper.selectList(null);
        return empList;
    }

    @Override
    public Emp getByEmpno(String empno) {
        return empMapper.getByEmpno(empno);
    }
}
