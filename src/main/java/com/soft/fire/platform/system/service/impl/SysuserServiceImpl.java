package com.soft.fire.platform.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft.fire.common.SqlFilter;
import com.soft.fire.platform.system.mapper.SysuserMapper;
import com.soft.fire.platform.system.model.Sysuser;
import com.soft.fire.platform.system.service.ISysuserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
    * 系统用户表 服务实现类
    * </p>
 *
 * @author David
 * @since 2020-04-06
 */
@Service
public class SysuserServiceImpl extends ServiceImpl<SysuserMapper, Sysuser> implements ISysuserService {

    /**
     * 注入mapper依赖
     */
    @Resource
    private SysuserMapper  sysuserMapper;

    @Override
    public IPage<Sysuser> findBySqlFilter(SqlFilter sqlFilter) {
          return null;
    }

}
