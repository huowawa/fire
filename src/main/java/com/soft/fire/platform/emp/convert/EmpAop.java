/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.convert;

import com.soft.fire.platform.emp.model.Emp;
import com.soft.fire.platform.emp.vo.EmpVo;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

/**
 * emp映射方法增强
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-05-02 10:54
 */
@Component
public class EmpAop {

    @BeforeMapping
    public void setEmpJob(Emp emp){
        emp.setJob("员工工作岗位："+emp.getJob());
    }

    @AfterMapping
    public void setRemark(Emp emp, @MappingTarget EmpVo empVo){
        empVo.setRemark(emp.getEmpname()+":"+emp.getRemark());
    }

}
