/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.convert;

import com.soft.fire.platform.emp.model.Emp;
import com.soft.fire.platform.emp.vo.EmpVo;

import javax.annotation.Resource;

/**
 * emp类型转换装饰器
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-05-02 9:35
 */
public abstract class EmpDecorator implements  EmpConvert {

    @Resource
    private EmpConvert delegate;

    @Override
    public EmpVo empToEmpVo(Emp emp) {
        EmpVo empVo = delegate.empToEmpVo(emp);
        empVo.setJob("当前员工的工作岗位是："+empVo.getEname());
        return empVo;
    }
}
