/*
 * Copyright (c)
 */
package com.soft.fire;

import com.soft.fire.platform.emp.vo.EmpVo;
import com.soft.fire.validator.CreateGroup;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Set;

/**
 * 数据效验测试用例
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-21 17:23
 */
@Slf4j
public class ValidationTest {

    @Test
    public void testEmpvo() {
        EmpVo empVo = new EmpVo();
        empVo.setEname("smith");
        empVo.setAge(0);
        empVo.setSal(30000);
        empVo.setSal(-1L);
        empVo.setDeptno(50);

        //引入校验工具
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        //获取校验器
        Validator validator = factory.getValidator();
        //执行校验
        Set<ConstraintViolation<EmpVo>> validateResult = validator.validate(empVo);
        //如果校验通过则返回的Set 对象集合长度为0

        if (validateResult.size() == 0) {
            log.info("效验通过啦!!!");
        }else{
            //使用Java8的  Lambda 表达式 简化
            validateResult.forEach(validate -> {
                log.info("验证失败的字段是：{}, 错误信息为：{}", validate.getPropertyPath(), validate.getMessage());
            });
        }



    }

    @Test
    public void testSave(){
        EmpVo empVo = new EmpVo();
        empVo.setEname("sm");
        empVo.setAge(29);
        empVo.setSal(-1);
       // empVo.setDeptno(50);
        empVo.setCreateTime(new Timestamp(System.currentTimeMillis()+20000));

        //引入校验工具
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        //获取校验器
        Validator validator = factory.getValidator();
        //使用默认分组校验
        Set<ConstraintViolation<EmpVo>> validateResult = validator.validate(empVo);
        //如果校验通过则返回的Set 对象集合长度为0

        //使用Java8的  Lambda 表达式 简化
        validateResult.forEach(validate -> {
            log.info("验证失败的字段是：{}, 错误信息为：{}", validate.getPropertyPath(), validate.getMessage());
           // log.info("问题级别为：{}",validate.getConstraintDescriptor().getPayload().toString());
        });

    }

}
