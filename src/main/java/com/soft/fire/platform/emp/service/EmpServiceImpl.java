/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft.fire.platform.emp.mapper.EmpMapper;
import com.soft.fire.platform.emp.model.Emp;
import com.soft.fire.common.SqlFilter;
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
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {
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

    @Override
    public int saveEmp(Emp emp) {
        return empMapper.insert(emp);
    }

    @Override
    public int updateEmp(Emp emp) {

        return empMapper.updateById(emp);
    }

    @Override
    public int removeEmp(Emp emp) {
        return empMapper.deleteById(emp.getEmpno());
    }

    @Override
    public IPage<Emp> selectPage() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("d.deptno","20");

        Page<Emp> page = new Page<>(1,5);

        IPage<Emp> resultPage = empMapper.selectPageInfo(page, queryWrapper);

        return resultPage;
    }

    @Override
    public IPage<Emp> findBySqlFilter(SqlFilter sqlFilter) {
        return empMapper.selectPageInfo(sqlFilter.getMyPage(),sqlFilter.getQueryWrapper());
    }

}
