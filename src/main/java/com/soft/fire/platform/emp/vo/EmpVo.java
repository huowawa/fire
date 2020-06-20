/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.soft.fire.annotation.MyAge;
import com.soft.fire.annotation.MyDeptNo;
import com.soft.fire.platform.dept.vo.DeptVo;
import com.soft.fire.validator.CreateGroup;
import com.soft.fire.validator.PayLoadLevel;
import com.soft.fire.validator.UpdateGroup;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.sql.Timestamp;

/**
 * 雇员信息对象Vo
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-21 17:03
 */
@Getter
@Setter
@MyDeptNo
public class EmpVo {
    /**
     * 员工编号
     */
    @NotNull(groups = {UpdateGroup.class}, message = "用来区分员工的标识不能为空!!")
    private Long empno;
    /**
     * 员工姓名
     */
    @NotBlank(groups = {CreateGroup.class, UpdateGroup.class})
    @Length(groups = {CreateGroup.class, UpdateGroup.class}, min = 3, max = 10)
    private String ename;
    /**
     * 工作岗位
     */
    private String job;
    /**
     * 领导者编号
     */
    private int mgr;
    /**
     * 入职时间
     * 自定义输出格式
     */
    @Past
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp hiredate;
    /**
     * 薪水  查询的时候不返回这个字段值
     */
    @Range(min = 0, max = 20000, message = "薪水不在范围内的啊",payload = PayLoadLevel.WARN.class)
    private double sal;
    /**
     * 奖金
     */
    private double comm;
    /**
     * 所在部门编号
     */
    private int deptno;
    /**
     * 年龄
     */
    @MyAge
    private int age;
    /**
     * 部门名称
     */
    private String empDeptName;

    /**
     * 创建时间
     */
    @NotNull(groups = {CreateGroup.class})
    @FutureOrPresent(groups = {CreateGroup.class}, message = "创建时间必须是当前或者将来时间!!")
    private Timestamp createTime;
    /**
     * 更新时间
     */
    @NotNull(groups = {UpdateGroup.class})
    @FutureOrPresent(groups = {UpdateGroup.class}, message = "更新时间必须是当前或者将来时间!!")
    private String updateTime;

    private String remark;

    private DeptVo deptVo;
}
