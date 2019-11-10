/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.model;

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
public class Emp {
    /**
     * 员工编号
     */
    private int empno;
    /**
     * 员工姓名
     */
    private String  ename;
    /**
     * 工作岗位
     */
    private String job;
    /**
     * 领导者编号
     */
    private int mgr ;
    /**
     * 入职时间
     * 自定义输出格式
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp hiredate;
    /**
     * 薪水
     */
    private double sal;
    /**
     * 奖金
     */
    private double comm;
    /**
     * 所在部门编号
     */
    private int deptno;
}
