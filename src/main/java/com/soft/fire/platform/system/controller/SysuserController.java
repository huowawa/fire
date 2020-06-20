package com.soft.fire.platform.system.controller;

import com.soft.fire.platform.system.service.ISysuserService;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
    * 系统用户表 前端控制器
    * </p>
 *
 * @author David
 * @since 2020-04-06
 */
@RestController
@RequestMapping("/system/sysuser")
public class SysuserController {
    /**
     * 日志操作对象
     */
   // public static final Logger logger = LoggerFactory.getLogger(SysuserController.class);

   /**
     * 注入service
     */
    @Resource
    private ISysuserService iSysuserService ;
}
