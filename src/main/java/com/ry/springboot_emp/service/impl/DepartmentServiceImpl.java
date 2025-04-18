package com.ry.springboot_emp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.springboot_emp.mapper.DepartmentMapper;
import com.ry.springboot_emp.pojo.Department;

import com.ry.springboot_emp.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 21142
 * @version 1.0
 * @description: 部门业务类
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper,Department> implements com.ry.springboot_emp.service.DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;


    /**
     * 查询所有部门
     * @return 部门集合
     */
    @Override
    public List<Department> getDepAll() {
        return this.list();
    }

    /**
     * 根据部门Id查询数量部门
     * @param id 部门Id
     * @return 数量
     */
    @Override
    public long getDepCountByDepId(int id) {
        QueryWrapper<Department> wrapper=new QueryWrapper<Department>()
                .eq("deptno",id);//自定义sql
        return this.count(wrapper);
    }
}
