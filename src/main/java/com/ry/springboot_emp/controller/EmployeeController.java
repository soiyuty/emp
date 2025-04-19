package com.ry.springboot_emp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ry.springboot_emp.controller.resouls.Code;
import com.ry.springboot_emp.controller.resouls.Result;
import com.ry.springboot_emp.exception.SystemException;
import com.ry.springboot_emp.pojo.Employee;
import com.ry.springboot_emp.pojo.vo.CriteriasEmp;
import com.ry.springboot_emp.service.EmployeeService;
import com.ry.springboot_emp.util.DeleteOSSImageUtil;
import com.ry.springboot_emp.util.UplodUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
/**
 * @author 21142
 * @version 1.0
 * @description: 员工控制类
 */
@RequestMapping("/emp")
@RestController
@CrossOrigin//CORS 跨域
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    //所有用户集合(弃用)
    @GetMapping
    public List<Employee> getAllEmp(){
        return employeeService.getAllEmp();
    }
    //查询所有员工个数 (弃用)
    @GetMapping("/c")
    public long getEmpAll(){
        return employeeService.getEmpCount();
    }
    //分页查询员工(弃用)
    @RequestMapping("/page")
    public List<Employee> getEmpPage(@RequestParam("page") int page, @RequestParam("perPage") int perPage){
        return employeeService.getEmpPage(page,perPage);
    }
    //根据员工姓名模糊查询
    @RequestMapping("/ObscureName")
    public Result etObscureName(@RequestParam("name") String name){
        List<Employee> obscureName = employeeService.getObscureName(name);
        return new Result(Code.OK,obscureName);
    }

    //添加员工
    @PostMapping
    public Result addEmp(@RequestParam("employee") String employee,@RequestParam(value = "file",required = false)MultipartFile file) throws JsonProcessingException { //允许文件为空
        //处理json数据
        ObjectMapper mapper = new ObjectMapper();
        Employee employee1 = mapper.readValue(employee, Employee.class);
        System.out.println(employee1);
        int row = employeeService.addEmp(employee1,file);
        return new Result(row > 0 ? Code.OK:Code.ERR,row,row > 0 ? "成功添加"+row+"条数据":"添加失败");
    }
    //多条件分页查询员工
    @PostMapping("/Cri")
    public Result getEmpCriterias(@RequestBody CriteriasEmp criteriasEmp, @RequestParam("page") int page, @RequestParam("perPage")int perPage){
        List<Employee> emps = employeeService.getEmpCriterias(criteriasEmp, page, perPage);
        return new Result(emps!=null ? Code.OK:Code.ERR,emps,emps!=null ? "":"服务器错误=>") ;
    }
    //多条件查询员工个数
    @RequestMapping("/CriCou")
    public Result getEmpCriteriasCount(@RequestBody CriteriasEmp criteriasEmp){
        long count = employeeService.getEmpCriteriasCount(criteriasEmp);
        return new Result(Code.OK,count);
    }
    //根据员工编号查询员工
    @GetMapping("/{id}")
    public Result getEmpById(@PathVariable("id") int id){
        Employee emp=employeeService.getEmpById(id);
        return new Result(emp!=null ? Code.OK:Code.ERR,emp,emp!=null ? "":"员工信息加载失败");
    }
    //修改员工信息
    @PutMapping("/{id}")
    public Result updateEmpById(@RequestBody Employee employee,@PathVariable("id")Long id){
        int row = employeeService.updateEmpById(employee);
        return new Result(row > 0 ? Code.OK:Code.ERR,row,row > 0 ? "成功修改"+row+"条数据":"服务器错误=>修改失败");
    }
    //删除员工信息
    @DeleteMapping
    public Result deleteEmp(@RequestBody List<Integer> ids) throws IOException {
        int row = employeeService.deleteEmp(ids);
        return new Result(row > 0 ? Code.OK:Code.ERR,row,row > 0 ? "删除成功":"服务器错误=>删除失败");
    }
    //修改员工头像信息
    @PostMapping("upImg")
    public Result updateEmpImage(@RequestParam("file")MultipartFile file,@RequestParam("id") int id){
        if (file.isEmpty()){
            return new Result(Code.INVALID_ARGUMENT,null,"文件不能为空");
        }
        Employee emp = employeeService.getEmpById(id);
        try {
            if (emp.getAvatarURL()!=""&&emp.getAvatarURL()!=null){ //如果有地址先删除在添加
                DeleteOSSImageUtil.deleteImage(emp.getAvatarURL());  //删除原文件
                String url = UplodUtil.uploadImage(file);
                employeeService.UpdateEmpImageUrl(url,id);
                return new Result(Code.OK,url);
            }else {
                String url = UplodUtil.uploadImage(file);
                employeeService.UpdateEmpImageUrl(url,id);
                return new Result(Code.OK,url);
            }

        }catch (IOException e){
            throw  new SystemException(Code.ERR,"系统故障，上传失败");
        }
    }



}
