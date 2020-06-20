package com.soft.fire.platform.dept.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
    * 
    * </p>
 *
 * @author David
 * @since 2020-04-06
 */
@Data
@TableName("DEPT")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门编码
     */
    @TableId("DEPTNO")
    private Integer deptno;

    /**
     * 部门名称
     */
    @TableField("DNAME")
    private String dname;

    /**
     * 部门位置
     */
    @TableField("LOC")
    private String loc;
    /**
     * 备注
     */
    @TableField(exist = false)
    private String remark;


}