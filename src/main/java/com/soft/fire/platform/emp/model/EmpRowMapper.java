/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 职员映射对象
 *
 * @author David Lin
 * @version: 1.0
 * @date 2019-03-30 18:17
 */
public class EmpRowMapper implements RowMapper<Emp> {

    @Override
    public Emp mapRow(ResultSet rs, int i) throws SQLException {

        Emp emp = new Emp();
       emp.setEmpno(rs.getInt("EMPNO"));
       emp.setEname(rs.getString("ENAME"));
       emp.setJob(rs.getString("JOB"));
       emp.setMgr(rs.getInt("MGR"));
       emp.setHiredate(rs.getTimestamp("HIREDATE"));
       emp.setSal(rs.getDouble("SAL"));
       emp.setComm(rs.getDouble("COMM"));
       emp.setDeptno(rs.getInt("DEPTNO"));
        return emp;
    }
}
