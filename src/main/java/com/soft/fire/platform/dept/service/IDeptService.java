package com.soft.fire.platform.dept.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.soft.fire.common.SqlFilter;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft.fire.platform.dept.model.Dept;

/**
 * <p>
    *  服务类
    * </p>
 *
 * @author David
 * @since 2020-04-06
 */
public interface IDeptService extends IService<Dept> {

 /**
     * 分页查询
     * @param sqlFilter
     * @return
     */
    IPage<Dept> findBySqlFilter(SqlFilter sqlFilter);

}
