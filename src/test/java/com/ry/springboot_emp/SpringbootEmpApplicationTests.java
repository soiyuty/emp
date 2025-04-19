package com.ry.springboot_emp;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ry.springboot_emp.pojo.Employee;
import com.ry.springboot_emp.service.DepartmentService;
import com.ry.springboot_emp.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
class SpringbootEmpApplicationTests {

@Autowired
RedisTemplate<String,Object> redisTemplate;
@Autowired
    private DepartmentService departmentService;
@Autowired
private EmployeeService employeeService;
    @Test
    public void t1(){
//        redisTemplate.opsForValue().set("hh","dawdwa",20, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("hh"));
    }
    @Test
    public void t2(){
        departmentService.getDepCountByDepId(10);
    }
    @Test
    public void t3(){
        List<Employee> allEmp = employeeService.getAllEmp();
//        long i=employeeService.getEmpCount();
        System.out.println(allEmp);
    }
    @Test
    public void t4(){
        Employee employee=new Employee();
        employee.setEmpno(1038);
        employee.setJob("职员");
        employeeService.updateById(employee);
    }
    @Test
    public void t5(){
        employeeService.removeById(1038);
    }
}
