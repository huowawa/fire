/*
 * Copyright (c)
 */
package com.soft.fire.platform.system.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户视图对象
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-04-06 10:30
 */
@Getter
@Setter
public class SysuserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String sysuserId;

    /**
     * 用户账号
     */
    private String sysuserAccount;

    /**
     * 用户姓名
     */
    private String sysuserName;

    /**
     * 用户状态(用户的状态用户的状态(-1:删除,0:禁用,1:正常,2:锁定)
     */
    private BigDecimal sysuserStatus;

    /**
     * 用户的创建时间
     */
    private Date createTime;

    /**
     * 用户的登录密码
     */
    private String sysuserPassword;

    /**
     * 用户的性别
     */
    private String sysuserSex;

    /**
     * 用户的手机号
     */
    private String sysuserMobile;

    /**
     * 用户所在部门ID
     */
    private String sysuserDepartid;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 年龄
     */
    private String age;
}
