/*
 * Copyright (c)
 */
package com.soft.fire.platform.developer.service;

import com.soft.fire.platform.developer.model.CodeGenerator;

/**
 * 代码生成器接口
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-03-13 22:05
 */
public interface ICodeGeneratorService {
    /**
     * 代码生成
     * @param codeGenerator 配置对象
     */
    void generateCode(CodeGenerator codeGenerator);
}
