package com.ry.springboot_emp.service;

import com.ry.springboot_emp.pojo.Department;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 21142
 * @version 1.0
 * @description: 部门业务逻辑接口
 */
public interface DepartmentService {
    //查询所有部门
    @Transactional(readOnly = true)
    List<Department> getDepAll();
    //根据部门ID查询个数
    @Transactional(readOnly = true)
    int getDepCountByDepId(int id);
}
