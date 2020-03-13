/*
 * Copyright (c)
 */
package com.soft.fire.platform.developer.controller;

import com.soft.fire.platform.developer.model.CodeGenerator;
import com.soft.fire.platform.developer.service.ICodeGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-13 22:02
 */
@RestController
public class CodeGeneratorController {
    /**
     * 日志操作对象
     */
    Logger logger = LoggerFactory.getLogger(CodeGeneratorController.class);

    /**
     * 代码生成
     */
    @Resource
    private ICodeGeneratorService codeGeneratorService;

    @RequestMapping("/codegen")
    public String codegen(HttpServletRequest request){

        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.setParentPackage("com.soft.fire.platform");
        codeGenerator.setAuthor("David");
        codeGenerator.setLombokModel(true);
        codeGenerator.setEnableTableFieldAnnotation(true);
        codeGenerator.setServiceClassNameStartWithI(true);
        codeGenerator.setProjectPath(System.getProperty("user.dir"));
        codeGenerator.setModuleName("system");
        codeGenerator.setEntityModuleName("model");
        codeGenerator.setMapperModuleName("mapper");

        List<String> tableList = new ArrayList<>();
        tableList.add("PLAT_SYSTEM_SYSUSER");

        codeGenerator.setTableNames(tableList);
        codeGenerator.setTablePrefix("PLAT_SYSTEM_");

        codeGenerator.setFieldPrefix(new ArrayList<>());

        codeGeneratorService.generateCode(codeGenerator);

        return "success";
    }

}
