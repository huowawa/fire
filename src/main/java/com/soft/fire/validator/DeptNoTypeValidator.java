/*
 * Copyright (c)
 */
package com.soft.fire.validator;

import com.soft.fire.annotation.MyDeptNo;
import com.soft.fire.platform.emp.vo.EmpVo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * 部门编号效验器
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-22 10:21
 */
public class DeptNoTypeValidator implements ConstraintValidator<MyDeptNo, EmpVo> {
    /**
     * 部门编号字典 ，可以改成枚举
     */
    private final List<Integer> deptNoList = Arrays.asList(new Integer[]{10, 20, 30, 40});

    @Override
    public void initialize(MyDeptNo constraintAnnotation) {
    }

    @Override
    public boolean isValid(EmpVo empVo, ConstraintValidatorContext constraintValidatorContext) {
        return deptNoList.contains(empVo.getDeptno());
    }
}
