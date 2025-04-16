package com.ry.springboot_emp.service.impl;

import com.ry.springboot_emp.mapper.DepartmentMapper;
import com.ry.springboot_emp.pojo.Department;

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
public class DepartmentServiceImpl implements com.ry.springboot_emp.service.DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;


    /**
     * 查询所有部门
     * @return 部门集合
     */
    @Override
    public List<Department> getDepAll() {

        return departmentMapper.selectDepAll();
    }

    /**
     * 根据部门Id查询数量部门
     * @param id 部门Id
     * @return 数量
     */
    @Override
    public int getDepCountByDepId(int id) {
        return departmentMapper.selectDepCountByDepId(id);
    }
}
