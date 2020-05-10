/*
 * Copyright (c)
 */
package com.soft.fire.platform.system.convert;

import com.soft.fire.platform.system.model.Sysuser;
import com.soft.fire.platform.system.vo.SysuserVo;
import org.mapstruct.Mapper;

/**
 * 用户对象映射器
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-04-12 11:45
 */
@Mapper
public interface SysUserConvert {

    Sysuser sysUserVoToSysuser(SysuserVo sysuserVo);
}
