package com.ry.springboot_emp.controller;

import com.ry.springboot_emp.controller.resouls.Code;
import com.ry.springboot_emp.controller.resouls.Result;
import com.ry.springboot_emp.pojo.Department;
import com.ry.springboot_emp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 21142
 * @version 1.0
 * @description: 部门控制类
 */
@RestController
@CrossOrigin
@RequestMapping("/dep")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public Result getDepAll(){
        List<Department> depAll = departmentService.getDepAll();
        return new Result(Code.OK,depAll,"niajji");
    }
}
