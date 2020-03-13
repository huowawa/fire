/*
 * Copyright (c)
 */
package com.soft.fire.platform.developer.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 代码生成器对象
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-13 22:04
 */
@Getter
@Setter
public class CodeGenerator {

    /**
     * 顶层包路径
     */
    private String parentPackage;
    /**
     * 代码作者
     */
    private String author;
    /**
     * 表前缀，配置后 生成的的代码都会把前缀去掉
     */
    private String tablePrefix;
    /**
     * 项目工程路径
     */
    private String projectPath;
    /**
     * 生成代码所在包名称
     */
    private String moduleName;
    /**
     * 实体对象所在模块包名称 默认 model
     */
    private String entityModuleName = "model";
    /**
     * mybatis Mapper接口所在包名称
     */
    private String mapperModuleName = "mapper";
    /**
     * 是否强制带上 @TableField 注解
     */
    private boolean enableTableFieldAnnotation = false;
    /**
     * 生成的Service 接口类名是否以I开头 默认是以I开头
     */
    private boolean serviceClassNameStartWithI = true;
    /**
     * 是否启用 Lombok
     */
    private boolean lombokModel = false;
    /**
     * 数据库表名称集合
     */
    private List<String> tableNames;
    /**
     * 是否去掉生成实体的属性名前缀
     * test_id -> id, test_type -> type
     */
    private List<String> fieldPrefix;
}
