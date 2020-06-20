package com.soft.fire.platform.system.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
    * 系统用户表
    * </p>
 *
 * @author David
 * @since 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("PLAT_SYSTEM_SYSUSER")
public class Sysuser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId("SYSUSER_ID")
    private String sysuserId;

    /**
     * 用户账号
     */
    @TableField("SYSUSER_ACCOUNT")
    private String sysuserAccount;

    /**
     * 用户姓名
     */
    @TableField("SYSUSER_NAME")
    private String sysuserName;

    /**
     * 用户状态(用户的状态用户的状态(-1:删除,0:禁用,1:正常,2:锁定)
     */
    @TableField("SYSUSER_STATUS")
    private BigDecimal sysuserStatus;

    /**
     * 用户的创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 用户的登录密码
     */
    @TableField("SYSUSER_PASSWORD")
    private String sysuserPassword;

    /**
     * 用户的性别
     */
    @TableField("SYSUSER_SEX")
    private String sysuserSex;

    /**
     * 用户的手机号
     */
    @TableField("SYSUSER_MOBILE")
    private String sysuserMobile;

    /**
     * 用户所在部门ID
     */
    @TableField("SYSUSER_DEPARTID")
    private String sysuserDepartid;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;

    /**
     * 年龄
     */
    private int age;


}