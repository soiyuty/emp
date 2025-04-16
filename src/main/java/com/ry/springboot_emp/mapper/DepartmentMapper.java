package com.ry.springboot_emp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ry.springboot_emp.pojo.Department;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 21142
 * @version 1.0
 * @description: 部门mapper接口
 */
@Transactional//开启事务
public interface DepartmentMapper extends BaseMapper<Department> {
    //根据所有部门
    @Select("select * from department")
    List<Department> selectDepAll();
    //根据部门Id查询
    @Select("select * from department where deptno=#{id}")
    Department selectDepById(@Param("id")int id);
    //根据部门ID查询个数
    @Select("select count(*) from department where deptno=#{id}")
    int selectDepCountByDepId(@Param("id")int id);
}
