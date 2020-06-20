package com.soft.fire.platform.dept.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft.fire.common.SqlFilter;
import com.soft.fire.platform.dept.mapper.DeptMapper;
import com.soft.fire.platform.dept.model.Dept;
import com.soft.fire.platform.dept.service.IDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
    *  服务实现类
    * </p>
 *
 * @author David
 * @since 2020-04-06
 */
@Service("iDeptService")
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    /**
     * 注入mapper依赖
     */
    @Resource
    private DeptMapper  deptMapper;

    @Override
    public IPage<Dept> findBySqlFilter(SqlFilter sqlFilter) {
          return null;
    }

}
