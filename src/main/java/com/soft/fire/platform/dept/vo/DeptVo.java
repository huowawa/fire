/*
 * Copyright (c)
 */
package com.soft.fire.platform.dept.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 部门对象
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-04-12 15:29
 */
@Getter
@Setter
public class DeptVo {

    /**
     * 部门编码
     */
    private Integer deptno;

    /**
     * 部门名称
     */
    private String dname;

    /**
     * 部门位置
     */
    private String loc;
    /**
     * 备注
     */
    private String remark;
}
