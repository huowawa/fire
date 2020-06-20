/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.convert;

import com.soft.fire.platform.dept.model.Dept;
import com.soft.fire.platform.dept.vo.DeptVo;
import com.soft.fire.platform.emp.model.Emp;
import com.soft.fire.platform.emp.vo.EmpVo;
import com.soft.fire.platform.system.model.Sysuser;
import com.soft.fire.utils.PlatStringUtil;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * 员工映射器接口
 *
 * @author David Lin
 * @version: 1.0
 * @date 2020-04-05 20:22
 */
@Mapper(componentModel = "spring", imports = PlatStringUtil.class)
public interface EmpConvert {

    EmpConvert INSTANCE = Mappers.getMapper(EmpConvert.class);

    /**
     * @param emp
     * @return
     */
    @Mapping(source = "empname", target = "ename",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
    @Mapping(source = "dept.dname", target = "empDeptName", defaultValue = "开发部门")
    @Mapping(source = "dept.deptno", target = "deptno")
    @Mapping(target = "updateTime", dateFormat = "yyyy-MM-dd", constant = "2020-05-02")
    @Mapping(source = "remark", target = "remark", defaultExpression = "java(PlatStringUtil.randomUUID())")
    EmpVo empToEmpVo(Emp emp);

    /**
     * @param dept
     * @return
     */
    DeptVo deptToDeptVo(Dept dept);

    /**
     *
     * @param empList
     * @return
     */
    //List<EmpVo> empsToEmpVos(List<Emp> empList);

    /*@Mapping(source = "ename",target = "empname")
    Emp empVoToEmp(EmpVo empVo);*/

    /**
     * 使用默认方法实现特殊的映射逻辑
     *
     * @param sysuser
     * @return
     */
    /*default SysuserVo sysUserToSysuserVo(Sysuser sysuser) {
        return null;
    }*/

    /**
     * 在使用@Mapping注释时，必须指定属性所在的参数。
     *
     * @param emp
     * @param dept
     * @return
     */
   /* @Mapping(source = "emp.empname", target = "ename")
    @Mapping(source = "emp.remark", target = "remark")
    @Mapping(source = "emp.deptno", target = "deptno")
    @Mapping(source = "dept.dname", target = "empDeptName")
    EmpVo empAndDeptToEmpVo(Emp emp, Dept dept);

    @Mapping(source = "emp.empname", target = "ename")
    @Mapping(source = "dname", target = "empDeptName")
    EmpVo fromEmp(Emp emp, String dname);*/

    /**
     * 更新已有的emp对象
     * @param empVo
     * @param emp
     */
    //void updateEmpFromVo(EmpVo empVo,@MappingTarget Emp emp);
}
