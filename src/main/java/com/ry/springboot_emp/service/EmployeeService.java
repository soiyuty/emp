package com.ry.springboot_emp.service;

import com.ry.springboot_emp.pojo.Employee;
import com.ry.springboot_emp.pojo.vo.CriteriasEmp;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 21142
 * @version 1.0
 * @description: 员工业务逻辑接口
 */

public interface EmployeeService {
    //查询全部员工
    @Transactional(readOnly = true)
    List<Employee> getAllEmp();
    //查询所有员工的个数
    @Transactional(readOnly = true)
    int getEmpCount();
    List<Employee> getEmpPage(int page, int perPage);
    //员工名称模糊查询
    @Transactional(readOnly = true)
    List<Employee> getObscureName(String name);
    //添加员工
    int addEmp(Employee employee,MultipartFile file);
    //多条件查询员工
    @Transactional(readOnly = true)
    List<Employee> getEmpCriterias(CriteriasEmp criteriasEmp,int page, int perPage);
    //多条件查询员工个数
    @Transactional(readOnly = true)
    int getEmpCriteriasCount(CriteriasEmp criteriasEmp);
    //根据员工编号查询员工
    @Transactional(readOnly = true)
    Employee getEmpById(int id);
    //修改员工信息
    int updateEmpById(Employee employee);
    //删除员工信息
    int deleteEmp(List<Integer> ids);
    //用户图片URl的修改
    int UpdateEmpImageUrl(String avatarURL,int id);
    //获取用户图片路径集合
    List<String> getEmpImageUrls(List<Integer> ids);
    public void getaaa();
}
