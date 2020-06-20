package com.soft.fire.platform.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.soft.fire.common.SqlFilter;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft.fire.platform.system.model.Sysuser;

/**
 * <p>
    * 系统用户表 服务类
    * </p>
 *
 * @author David
 * @since 2020-04-06
 */
public interface ISysuserService extends IService<Sysuser> {

 /**
     * 分页查询
     * @param sqlFilter
     * @return
     */
    IPage<Sysuser> findBySqlFilter(SqlFilter sqlFilter);

}
