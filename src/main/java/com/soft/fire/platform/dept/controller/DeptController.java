package com.soft.fire.platform.dept.controller;

import com.soft.fire.platform.dept.service.IDeptService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
    *  前端控制器
    * </p>
 *
 * @author David
 * @since 2020-04-06
 */
@RestController
@RequestMapping("/dept/dept")
public class DeptController {
    /**
     * 日志操作对象
     */
   // public static final Logger logger = LoggerFactory.getLogger(DeptController.class);

   /**
     * 注入service
     */
    @Resource
    private IDeptService iDeptService ;
}
