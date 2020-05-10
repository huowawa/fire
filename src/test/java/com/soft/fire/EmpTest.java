/*
 * Copyright (c)
 */
package com.soft.fire;

import com.soft.fire.platform.dept.model.Dept;
import com.soft.fire.platform.emp.convert.EmpConvert;
import com.soft.fire.platform.emp.model.Emp;
import com.soft.fire.platform.emp.vo.EmpVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Java8引子
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-04-08 22:35
 */
@Slf4j
public class EmpTest {

    /**
     * 找出薪水大于15K的员工
     *
     * @param empList
     * @return
     */
    public static List<Emp> filterSale(List<Emp> empList) {
        List<Emp> resultList = new ArrayList<>();
        for (Emp emp : empList) {
            if (emp.getSal() >= 15000) {
                resultList.add(emp);
            }
        }
        return resultList;
    }

    /**
     * 找出部门编号是20的员工
     *
     * @param empList
     * @return
     */
    public static List<Emp> filterDeptNo(List<Emp> empList, Predicate predicate) {
        List<Emp> resultList = new ArrayList<>();
        for (Emp emp : empList) {
            if (emp.getDeptno() == 20) {
                resultList.add(emp);
            }
        }
        return resultList;
    }

    @Test
    public void testEmp(){
        Emp emp = new Emp();
        emp.setEmpname("smith");
        emp.setEmpno(123L);
        emp.setJob("开发");

        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        emp.setUpdateTime(updateTime);


        EmpVo empVo = EmpConvert.INSTANCE.empToEmpVo(emp);
        log.info("the empVo updateTiem is {}",empVo.getUpdateTime());
    }
}
