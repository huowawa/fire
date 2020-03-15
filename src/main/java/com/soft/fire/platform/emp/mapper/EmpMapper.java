/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft.fire.platform.emp.model.Emp;
import org.apache.ibatis.annotations.Param;

/**
 * 员工数据操作
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-02-22 17:05
 */
public interface EmpMapper  extends BaseMapper<Emp> {
    /**
     * 根据编号获取员工信息
     * @param empno
     * @return
     */
    Emp getByEmpno(String empno);

    /**
     * 分页查询
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<Emp> selectPageInfo(Page<Emp> page,  @Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
