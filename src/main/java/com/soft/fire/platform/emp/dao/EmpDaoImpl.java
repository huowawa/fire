/*
 * Copyright (c)
 */
package com.soft.fire.platform.emp.dao;

import com.soft.fire.platform.emp.model.Emp;
import com.soft.fire.platform.emp.model.EmpRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 雇员信息数据操作接口实现类
 *
 * @author David Lin
 * @version: 1.0
 * @date 2019-03-17 11:26
 */
@Repository("empDao")
public class EmpDaoImpl implements  EmpDao {
    /**
     * 注入jdbc操作依赖
     */
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据名称 获取雇员信息
     *
     * @param ename
     * @return
     */
    @Override
    public  Map<String,Object> getEmpByName(String ename) {
        StringBuilder sql = new StringBuilder(32);
        sql.append("SELECT  E.* FROM EMP E WHERE E.ENAME=?");
        return jdbcTemplate.queryForMap(sql.toString(),new Object[]{ename});
    }

    @Override
    public Emp getEmpByNo(String empno) {
        StringBuilder sql = new StringBuilder(32);
        sql.append("SELECT  E.* FROM EMP E WHERE E.EMPNO=?");
        return jdbcTemplate.queryForObject(sql.toString(),new EmpRowMapper(),empno);
    }

    /**
     * 获取任务
     *
     * @param exeId
     * @return
     */
    @Override
    public List<Map<String, Object>> findTaskByExeId(String exeId) {
        StringBuilder sqlBuilder = new StringBuilder(32);
        sqlBuilder.append("select t.*  from jbpm6_task t where t.exe_id=?");
        return this.jdbcTemplate.queryForList(sqlBuilder.toString(),exeId);
    }

    /**
     * 获取所有职员信息
     *
     * @return
     */
    @Override
    public  List<Emp> findAllEmp() {
        StringBuilder sqlBuilder = new StringBuilder(32);
        sqlBuilder.append("SELECT  E.* FROM EMP E ");
        return this.jdbcTemplate.query(sqlBuilder.toString(),new EmpRowMapper());
    }

    @Override
    public void removeEmp(Integer empno) {
        StringBuilder sqlBuilder = new StringBuilder(32);
        sqlBuilder.append("DELETE FROM EMP E WHERE E.EMPNO=?");
        this.jdbcTemplate.update(sqlBuilder.toString(),empno);
    }

    @Override
    public int saveEmp(Emp emp) {
        StringBuilder sqlBuilder = new StringBuilder(32);
        sqlBuilder.append("insert into emp(empno,ename,job) values(?,?,?)");
        return this.jdbcTemplate.update(sqlBuilder.toString(),emp.getEmpno(),emp.getEname(),emp.getJob());
    }
}
