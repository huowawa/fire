package com.soft.fire.annotation;


import com.soft.fire.validator.DeptNoTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {DeptNoTypeValidator.class}) //指定校验器
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyDeptNo {
    //自定义提示信息
    String message() default "部分编号只能是10、20、30、40等几个编号!!";

    //自定义分组 将 validator 进行分类，不同的类 group 中会执行不同的 validator 操作
    Class<?>[] groups() default {};

    //指定  效验问题的级别
    Class<? extends Payload>[] payload() default {};
}
