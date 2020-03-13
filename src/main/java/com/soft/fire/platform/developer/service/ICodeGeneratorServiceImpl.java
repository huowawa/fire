/*
 * Copyright (c)
 */
package com.soft.fire.platform.developer.service;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.soft.fire.config.OracleProperties;
import com.soft.fire.platform.developer.model.CodeGenerator;
import com.soft.fire.util.FreemarkerTemplateEngine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Driver;
import java.util.*;

/**
 * 代码生成器实现
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-13 22:05
 */
@Service
public class ICodeGeneratorServiceImpl implements ICodeGeneratorService {

    /**
     * 注入Oracle数据库配置对象
     */
    @Resource
    private OracleProperties oracleProperties;

    @Override
    public void generateCode(CodeGenerator codeGenerator) {

        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = getGlobalConfig(codeGenerator);

        // 数据源配置
        DataSourceConfig dataSourceConfig = getDataSourceConfig();

        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig(codeGenerator);


        //配置模板
        TemplateConfig templateConfig = getTemplateConfig();
        //包配置
        PackageConfig packageConfig = getPackageConfig(codeGenerator);

        // 自定义配置
        InjectionConfig injectionConfig = getInjectionConfig(packageConfig);

        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setStrategy(strategyConfig);
        //配置自定义模板
        autoGenerator.setTemplate(templateConfig);
        //配置自定义属性注入
        autoGenerator.setCfg(injectionConfig);
        autoGenerator.setPackageInfo(packageConfig);

        //使用 Freemarker模版引擎
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();
    }

    /**
     * 包配置
     *
     * @param codeGenerator
     * @return
     */
    private PackageConfig getPackageConfig(CodeGenerator codeGenerator) {
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(codeGenerator.getModuleName());
        pc.setParent(codeGenerator.getParentPackage());
        pc.setController("controller");
        pc.setEntity(codeGenerator.getEntityModuleName());
        pc.setService("service");
        pc.setMapper(codeGenerator.getMapperModuleName());

        return pc;
    }

    /**
     * 自定义配置
     *
     * @return
     */
    private InjectionConfig getInjectionConfig(PackageConfig pc) {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置优先于默认配置生效
        focList.add(new FileOutConfig("/templates/code/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义mapper xml输出目录
                return System.getProperty("user.dir") + "/src/main/resources/mapper/" + pc.getModuleName() +
                        "/" + tableInfo.getMapperName() + StringPool.DOT_XML;
            }
        });

        injectionConfig.setFileOutConfigList(focList);

        return injectionConfig;
    }

    /**
     * 指定自定义模板路径
     *
     * @return
     */
    private TemplateConfig getTemplateConfig() {
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setEntity("templates/code/entity.java");
        templateConfig.setController("templates/code/controller.java");
        templateConfig.setMapper("templates/code/mapper.java");
        //关闭默认的mapper xml生成
        templateConfig.setXml(null);
        templateConfig.setService("templates/code/service.java");
        templateConfig.setServiceImpl("templates/code/serviceImpl.java");

        return templateConfig;
    }

    /**
     * 策略配置
     *
     * @return
     */
    private StrategyConfig getStrategyConfig(CodeGenerator codeGenerator) {
        StrategyConfig strategyConfig = new StrategyConfig();

        strategyConfig.setCapitalMode(true);
        strategyConfig.setRestControllerStyle(true);
        //设置数据库表映射到实体的命名策略
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        //设置数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        //是否生成实体时，生成字段注解
        strategyConfig.setEntityTableFieldAnnotationEnable(codeGenerator.isEnableTableFieldAnnotation());
        //是否启用 Lombok
        strategyConfig.setEntityLombokModel(codeGenerator.isLombokModel());
        //表前缀，配置后 生成的的代码都会把前缀去掉
        strategyConfig.setTablePrefix(codeGenerator.getTablePrefix());

        //test_id -> id, test_type -> type
        String[] fieldPrefix = new String[codeGenerator.getFieldPrefix().size()];
        codeGenerator.getFieldPrefix().toArray(fieldPrefix);
        strategyConfig.setFieldPrefix(fieldPrefix);

        //修改替换成你需要的表名，多个表名传数组
        String[] tableNames = new String[codeGenerator.getTableNames().size()];
        codeGenerator.getTableNames().toArray(tableNames);
        strategyConfig.setInclude(tableNames);

        return strategyConfig;
    }

    /**
     * 数据源配置
     *
     * @return
     */
    private DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();

        dataSourceConfig.setDbType(DbType.ORACLE);
        dataSourceConfig.setUrl(oracleProperties.getUrl());
        dataSourceConfig.setUsername(oracleProperties.getUsername());
        dataSourceConfig.setPassword(oracleProperties.getPassword());
        dataSourceConfig.setDriverName(Driver.class.getName());

        return dataSourceConfig;
    }

    /**
     * 全局配置
     *
     * @param codeGenerator
     * @return
     */
    private GlobalConfig getGlobalConfig(CodeGenerator codeGenerator) {
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();

        globalConfig.setActiveRecord(false);
        globalConfig.setAuthor(codeGenerator.getAuthor());
        //生成文件的输出目录
        globalConfig.setOutputDir(codeGenerator.getProjectPath() + "/src/main/java");
        //是否覆盖已有文件
        globalConfig.setFileOverride(true);
        //开启 BaseResultMap
        globalConfig.setBaseResultMap(true);
        //开启 baseColumnList
        globalConfig.setBaseColumnList(true);
        //是否生成完成后打开资源管理器
        globalConfig.setOpen(true);
        //日期类型的字段使用哪个类型，默认是 java8的 日期类型，此处改为 java.util.date
        globalConfig.setDateType(DateType.ONLY_DATE);

        //生成的Service 接口类名是否以I开头
        if (!codeGenerator.isServiceClassNameStartWithI()) {
            globalConfig.setServiceName("%sService");
        }

        return globalConfig;
    }
}
