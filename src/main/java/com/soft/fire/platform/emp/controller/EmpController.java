/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.soft.fire.common.SqlFilter;
import com.soft.fire.platform.emp.convert.EmpConvert;
import com.soft.fire.platform.emp.model.Emp;
import com.soft.fire.platform.emp.service.EmpService;
import com.soft.fire.platform.emp.vo.EmpVo;
import com.soft.fire.utils.PlatStringUtil;
import org.apache.tomcat.jni.File;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * 雇员控制器
 *
 * @author David Lin
 * @version: 1.0
 * @date 2019-03-17 11:29
 */
@RestController
@Validated
public class EmpController {

    private Map<String, String> hashMap = new ConcurrentHashMap<>();

    /**
     * 日志操作对象
     */
    Logger logger = LoggerFactory.getLogger(EmpController.class);
    /**
     * 员工业务操作对象
     */
    @Resource
    private EmpService empService;

    /**
     * 注入emp映射器
     */
    @Resource
    private EmpConvert empConvert;

    @RequestMapping("/auth")
    public String authorization(@Length(min = 5, message = "Clientid长度至少11位") @RequestParam("clientid") String clientid,
                                @NotBlank(message = "口令不能为空") @RequestParam("pass") String pass) {
        if ("admin".equals(clientid) && "123456".equals(pass)) {
            String uuid = PlatStringUtil.randomUUID();
            hashMap.put("token", uuid);
            return uuid;
        }
        return "error";
    }

    @GetMapping("/empall")
    public List<Emp> findAll(HttpServletRequest request) {
        List<Emp> list = empService.list(null);
        return filterEmp(list, (Emp emp) -> 20 == emp.getDeptno());
    }

  /*  @GetMapping("/emp")
    public EmpVo getEmp(HttpServletRequest request) {
        Emp emp = empService.getByEmpno("592");
        logger.info(emp.toString());
        return empConvert.empToEmpVo(emp);
    }*/

    /**
     * 保存雇员信息
     *
     * @return
     */
/*    @PostMapping("/saveemp")
    public Integer saveEmp(@RequestBody  EmpVo empVo,HttpServletRequest request) {
        String serverToken = hashMap.get("token");
        String clientToken = request.getHeader("token");
        if(StringUtil.isBlank(clientToken)||!clientToken.equals(serverToken)){
            throw new APIException("token错误!");
        }
        String remResult = hashMap.remove("token");
        if(StringUtil.isBlank(remResult)){
            throw new APIException("请不要重复提交!");
        }
        logger.info("remresult is {}",remResult);

        Emp emp = empConvert.empVoToEmp(empVo);
        logger.info(emp.toString());
        return empService.saveEmp(emp);
    }*/
    public Integer updateEmp(HttpServletRequest request) {
        return empService.updateEmp(null);
    }

    public Integer removeEmp(HttpServletRequest request) {
        return empService.removeEmp(null);
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request) {
        Emp emp = new Emp();
        emp.setHiredate(new Timestamp(System.currentTimeMillis()));
        emp.setSal(26000);

        /**
         * 根据条件更新需要使用 条件构造器  QueryWrapper或者 UpdateWrapper
         */
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ename", "fire2");
        queryWrapper.ge("sal", 200);

        /**
         * 或者
         */
        UpdateWrapper<Emp> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("ename", "fire2");

        boolean result = empService.update(emp, updateWrapper);


        logger.info("the exec result is {}", result + "");
        return "success";
    }

    @RequestMapping("/remove")
    public String remove(HttpServletRequest request) {
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ename", "fire2");

        boolean result = empService.remove(queryWrapper);


        return "success" + result;
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @RequestMapping("/pages")
    public IPage<Emp> selectPage(HttpServletRequest request) {
        SqlFilter<Emp> sqlFilter = new SqlFilter<Emp>(request);
        sqlFilter.addFilter("e.empno", "asc", SqlFilter.FILTER_TYPE_ORDER);

        IPage<Emp> empIPage = empService.findBySqlFilter(sqlFilter);


        logger.info("数据总条数:" + empIPage.getTotal());
        logger.info("数据总页数：" + empIPage.getPages());

        return empIPage;
    }

    @RequestMapping("list")
    public List<Emp> listEmp(HttpServletRequest request) {

        QueryWrapper<Emp> queryWrapper = new QueryWrapper();
        queryWrapper.eq("deptno", 20);
        queryWrapper.select("empno,ename,job,deptno");

        Emp emp = new Emp();

        return emp.selectList(queryWrapper);
    }

    @RequestMapping("/testEmp")
    public EmpVo testTime() {
        Emp emp = new Emp();
        emp.setEmpname(null);
        emp.setEmpno(123L);
        emp.setJob("开发");

        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        emp.setUpdateTime(updateTime);

        EmpVo empVo = empConvert.empToEmpVo(emp);
        logger.info("the empVo updateTiem is {}", empVo.getUpdateTime());
        return empVo;
    }


    /**
     * 筛选出大于指定薪水值的员工
     *
     * @param empList 员工列表
     * @param sale    指定的薪水
     * @return
     */
    public List<Emp> filterSale(List<Emp> empList, double sale) {
        List<Emp> resultList = new ArrayList<>();
        for (Emp emp : empList) {
            if (emp.getSal() >= sale) {
                resultList.add(emp);
            }
        }
        return resultList;
    }

    /**
     * 通过flag判断是筛选部门编号还是筛选薪水
     *
     * @param empList
     * @param deptNo
     * @param sale
     * @param flag
     * @return
     */
    public List<Emp> filterDeptNo(List<Emp> empList, int deptNo, double sale, boolean flag) {
        List<Emp> resultList = new ArrayList<>();
        for (Emp emp : empList) {
            if ((flag && emp.getDeptno() == deptNo)
                    || (!flag && (emp.getSal() >= sale))) {
                resultList.add(emp);
            }
        }
        return resultList;
    }

    /**
     * 员工过滤器
     *
     * @param empList
     * @param predicate
     * @return
     */
    public List<Emp> filterEmp(List<Emp> empList, Predicate<Emp> predicate) {
        List<Emp> resultList = new ArrayList<>();
        for (Emp emp : empList) {
            if (predicate.test(emp)) {
                resultList.add(emp);
            }
        }
        return resultList;
    }

}
