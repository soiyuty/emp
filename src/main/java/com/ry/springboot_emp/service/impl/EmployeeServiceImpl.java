package com.ry.springboot_emp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.springboot_emp.controller.resouls.Code;
import com.ry.springboot_emp.exception.BusinessException;
import com.ry.springboot_emp.exception.SystemException;
import com.ry.springboot_emp.mapper.EmployeeMapper;
import com.ry.springboot_emp.pojo.Employee;
import com.ry.springboot_emp.pojo.vo.CriteriasEmp;
import com.ry.springboot_emp.service.DepartmentService;
import com.ry.springboot_emp.service.EmployeeService;
import com.ry.springboot_emp.util.DeleteOSSImageUtil;
import com.ry.springboot_emp.util.UplodUtil;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 21142
 * @version 1.0
 * @description: 员工业务类
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService{
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentService departmentService;


    /**
     * 查询所有员工
     * @return 员工集合
     */
    @Override
    public List<Employee> getAllEmp() {
        return this.list();
    }

    /**
     * 查询所有员工个数
     * @return 数量
     */
    @Override
    public long getEmpCount() {
        return this.count();
    }

    /**
     * 分页查询员工
     * @param page 第几页
     * @param perPage 每页几条
     * @return 员工集合
     */
    @Override //待修改
    public List<Employee> getEmpPage(int page, int perPage) {  //嵌套结果
        page=(page-1)*perPage;
        return employeeMapper.selectEmpPage(page,perPage);
    }






    /**
     * 根据员工姓名模糊查询
     * @param name 姓名
     * @return 集合
     */
    @Override
    public List<Employee> getObscureName(String name) {
        QueryWrapper<Employee> wrapper=new QueryWrapper<Employee>()
                .select("ename")
                .like("ename",name);
        return employeeMapper.selectList(wrapper);
    }

    /**
     * 添加用户
     * @param employee 用户信息
     * @return 受影响的行数
     */
    @Override
    public int addEmp(Employee employee,MultipartFile file) {
        if (employee.getHiredate()==null||employee.getHiredate()==""){//判断时间
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            employee.setHiredate(format.format(new Date()));
        }
        if (employee.getEname()!=null && employee.getEname()!=""){ //判断名称是否为空
            //判断部门Id是否存在
            if (departmentService.getDepCountByDepId(employee.getDepartment().getDeptno())>0){
                try {
                    employee.setDeptno(employee.getDepartment().getDeptno());
                   int rows = employeeMapper.insert(employee);
                   if (file!=null){ //判断是否传入了员工头像
                       String url=UplodUtil.uploadImage(file);
                       //写入url
                       this.UpdateEmpImageUrl(url,employee.getEmpno());
                   }
                    return rows;
                }catch (Exception e){
                    throw new SystemException(Code.ERR,"系统繁忙");
                }

            }else {
                throw new  BusinessException(Code.INVALID_ARGUMENT,"部门不存在,请刷新后重试"); //进业务自定义异常
            }
        }else {
            throw new  BusinessException(Code.INVALID_ARGUMENT,"用户名不能为空");//进业务自定义异常
        }
    }

    /**
     * 多条件查询
     * @param criteriasEmp 查询条件属性
     * @param page 页数
     * @param perPage 最大条数
     * @return 集合
     */
    @Override
    public List<Employee> getEmpCriterias(CriteriasEmp criteriasEmp,int page,int perPage) {  //嵌套结果
        if (page!=0&&perPage!=0){
            page=(page-1)*perPage;
            return employeeMapper.selectEmpCriterias(criteriasEmp,page,perPage);
        }else {
            return null;
        }
    }

    /**
     * 多条件查询个数
     * @param criteriasEmp 条件属性
     * @return 数量
     */
    @Override
    public long getEmpCriteriasCount(CriteriasEmp criteriasEmp) {
        QueryWrapper<Employee> wrapper=new QueryWrapper<Employee>()
                .like(criteriasEmp.getName()!=null&&!criteriasEmp.getName().equals(""),"ename",criteriasEmp.getName())
                .like(criteriasEmp.getJob()!=null&&!criteriasEmp.getJob().equals(""),"job",criteriasEmp.getJob())
                .gt(criteriasEmp.getBeginData()!=null&&!criteriasEmp.getBeginData().equals(""),"hiredate",criteriasEmp.getBeginData())
                .lt(criteriasEmp.getEndData()!=null&&!criteriasEmp.getEndData().equals(""),"hiredate",criteriasEmp.getEndData())
                .eq(criteriasEmp.getDep()!=0,"deptno",criteriasEmp.getDep());
        return this.count(wrapper);
    }

    /**
     * 根据员工编号查询员工
     * @param id 员工编号
     * @return 员工对象
     */
    @Override
    public Employee getEmpById(int id) { //嵌套结果
        return employeeMapper.selectEmpById(id);
    }

    /**
     * 修改员工信息
     * @param employee 员工对象属性
     * @return 受影响的行数
     */
    @Override
    public int updateEmpById(Employee employee) {
        if(employee.getEname()!=null&&employee.getEname()!=""){
            //判断部门是否存在
            if (departmentService.getDepCountByDepId(employee.getDepartment().getDeptno())==0){
                throw new BusinessException(Code.INVALID_ARGUMENT,"部门不存在,请刷新重试");
            }
            try {
                UpdateWrapper<Employee> updateWrapper=new UpdateWrapper<Employee>();
                updateWrapper.eq("empno",employee.getEmpno())
                        .set(employee.getEname()!=null&&!employee.getEname().equals(""),"ename",employee.getEname())
                        .set("job",employee.getJob())
                        .set("hiredate",employee.getHiredate())
                        .set("sal",employee.getSal())
                        .set("comm",employee.getComm())
                        .set(employee.getDepartment().getDeptno()!=0,"deptno",employee.getDepartment().getDeptno());
                int rows = employeeMapper.update(updateWrapper);

                employee.setEname(employee.getEname().trim());
                return rows;
            }catch (Exception e){
                throw new SystemException(Code.ERR,"系统繁忙，请稍后重试");
            }
        }else {
                throw new  BusinessException(Code.INVALID_ARGUMENT,"用户名不能为空");//进业务自定义异常
        }

    }
    /**
     * 删除员工信息
     * @param ids 员工Id
     * @return 受影响的行数
     */
    @Override
    public int deleteEmp(List<Integer> ids) throws IOException {
        if (!ids.isEmpty()){
            //查询出有数据的id
            QueryWrapper<Employee> wrapper=new QueryWrapper<>();
            wrapper.select("empno")
                    .in("empno",ids);
            List<Integer> collect = employeeMapper.selectList(wrapper)
                    .stream().map(Employee::getEmpno)
                    .collect(Collectors.toList()); //筛选有数据的id 返回整型数组
                if (collect.size()>1){ //删除多个
                    List<String> UrlNames =this.getEmpImageUrls(collect);
                    List<String> FileName=new ArrayList<>();
                    for (String url:UrlNames){ //便利url集合获取文件名
                        if (url!=null&&!url.equals("")){
                            URI uri = URI.create(url);
                            FileName.add(Paths.get(uri.getPath()).getFileName().toString());
                        }
                    }
                    boolean row = this.removeByIds(collect);
                    DeleteOSSImageUtil.deleteImages(FileName);//执行批量删除文件

                    return row ? 1:0;
                }else { // 删除单个
                    Employee employee=employeeMapper.selectEmpById(ids.get(0));
                    if (employee==null){
                        throw new BusinessException(Code.INVALID_ARGUMENT,"员工不存在");
                    }
                    boolean rows = removeById(ids.get(0));
                    if(employee.getAvatarURL()!=null&&employee.getAvatarURL()!=""){
                        URI uri = URI.create(employee.getAvatarURL());
                        String fileName = Paths.get(uri.getPath()).getFileName().toString();
                        DeleteOSSImageUtil.deleteImage(fileName);
                        return rows ? 1:0;
                    }else {
                        return rows ? 1:0;
                    }
                }
        }else {
            return 0;
        }

    }

    /**
     * 用户头像路径的修改
     * @param avatarURL url
     * @param id 用户ID
     * @return 首影响的行数
     */
    @Override
    public int UpdateEmpImageUrl(String avatarURL, int id) {
        return employeeMapper.UpdateEmpImageUrl(avatarURL,id);
    }

    /**
     * 获取用户头像地址的集合
     * @param ids id集合
     * @return 集合
     */
    @Override
    public List<String> getEmpImageUrls(List<Integer> ids) {
        return employeeMapper.selectEmpImageUrls(ids);
    }


}
