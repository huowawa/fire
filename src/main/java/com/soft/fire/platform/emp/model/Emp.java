/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * 雇员实体对象
 *
 * @author David Lin
 * @version: 1.0
 * @date 2019-03-17 11:30
 */
@Setter
@Getter
@ToString
@TableName("EMP")
@KeySequence("EMP_SEQUENCE")
public class Emp {
    /**
     * 员工编号
     */
    @TableId(type = IdType.INPUT)
    private Long empno;
    /**
     * 员工姓名
     */
    @TableField("ename")
    private String empname;
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
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp hiredate;
    /**
     * 薪水  查询的时候不返回这个字段值
     */
    @TableField(select = false)
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
     * 地址
     */
    @TableField(exist = false)
    private String empAddress;
    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String dname;

}
