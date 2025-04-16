package com.ry.springboot_emp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ry.springboot_emp.pojo.Employee;
import com.ry.springboot_emp.pojo.vo.CriteriasEmp;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 21142
 * @version 1.0
 * @description: 员工mapper接口
 */
@Transactional//开启事务
public interface EmployeeMapper extends BaseMapper<Employee> {
//    查询所有员工
    @Select("SELECT e.*,d.dname,d.loc FROM employee e LEFT JOIN department d ON e.deptno=d.deptno")
    @Results({
            @Result(id = true,property = "empno",column = "empno"),
            @Result(property = "ename",column = "ename"),
            @Result(property = "job",column = "job"),
            @Result(property = "hiredate",column = "hiredate"),
            @Result(property = "sal",column = "sal"),
            @Result(property = "comm",column = "comm"),
            @Result(property = "avatarURL",column = "avatarURL"),
            //部门表字段
            @Result(property = "department.deptno",column = "deptno"),
            @Result(property = "department.dname",column = "dname"),
            @Result(property = "department.loc",column = "loc")
    })
    List<Employee> selectEmpAll();
    //查询所有员工的个数
    @Select("select count(*) from employee")
    int selectEmpCount();
    //分页查询员工
    @Select("select e.*,d.dname,d.loc FROM employee e LEFT JOIN department d ON e.deptno=d.deptno ORDER BY e.empno LIMIT #{page},#{perPage}")
    @Results({
            @Result(id = true,property = "empno",column = "empno"),
            @Result(property = "ename",column = "ename"),
            @Result(property = "job",column = "job"),
            @Result(property = "hiredate",column = "hiredate"),
            @Result(property = "sal",column = "sal"),
            @Result(property = "comm",column = "comm"),
            @Result(property = "avatarURL",column = "avatarURL"),
            //部门表字段
            @Result(property = "department.deptno",column = "deptno"),
            @Result(property = "department.dname",column = "dname"),
            @Result(property = "department.loc",column = "loc")
    })
    List<Employee> selectEmpPage(@Param("page") int page,@Param("perPage") int perPage);

    //员工名称模糊查询
    @Select("select * from employee where ename like concat('%',#{name},'%')")
    List<Employee> selectObscureName(@Param("name")String name);
    //添加员工
    @Insert("insert employee(ename,job,hiredate,sal,comm,deptno) value(#{ename},#{job},#{hiredate},#{sal},#{comm},#{department.deptno})")
    @Options(useGeneratedKeys = true, keyProperty = "empno", keyColumn = "empno")//返回主键
    int InsertEmp(Employee employee);
    //多条件查询员工
    @Select("<script>SELECT e.*,d.dname,d.loc FROM employee e LEFT JOIN department d ON e.deptno=d.deptno " +
            "<where>" +
            "<if test='criteriasEmp.name != null and criteriasEmp.name != \"\"'> and ename like concat('%',#{criteriasEmp.name},'%') </if>" +
            "<if test='criteriasEmp.job!=null and criteriasEmp.job != \"\"'> and job like concat('%',#{criteriasEmp.job},'%')</if>" +
            "<if test='criteriasEmp.beginData!=null and criteriasEmp.beginData!= \"\"'> and hiredate &gt;= #{criteriasEmp.beginData}</if>" +
            "<if test='criteriasEmp.endData!=null and criteriasEmp.endData != \"\"'> and hiredate &lt;= #{criteriasEmp.endData}</if>" +
            "<if test='criteriasEmp.dep!=null and criteriasEmp.dep != \"\"'> and d.deptno=#{criteriasEmp.dep}</if>" +
            "</where> ORDER BY e.empno LIMIT #{page},#{perPage}</script>")
    @Results({
            @Result(id = true,property = "empno",column = "empno"),
            @Result(property = "ename",column = "ename"),
            @Result(property = "job",column = "job"),
            @Result(property = "hiredate",column = "hiredate"),
            @Result(property = "sal",column = "sal"),
            @Result(property = "comm",column = "comm"),
            @Result(property = "avatarURL",column = "avatarURL"),
            //部门表字段
            @Result(property = "department.deptno",column = "deptno"),
            @Result(property = "department.dname",column = "dname"),
            @Result(property = "department.loc",column = "loc")
    })
    List<Employee> selectEmpCriterias(@Param("criteriasEmp") CriteriasEmp criteriasEmp,@Param("page") int page,@Param("perPage") int perPage);
    //多条件查询员工个数
    @Select("<script>SELECT count(*) FROM employee e LEFT JOIN department d ON e.deptno=d.deptno " +
            "<where>"+
            "<if test='criteriasEmp.name != null and criteriasEmp.name != \"\"'> and ename like concat('%',#{criteriasEmp.name},'%') </if>" +
            "<if test='criteriasEmp.job!=null and criteriasEmp.job != \"\"'> and job like concat('%',#{criteriasEmp.job},'%')</if>" +
            "<if test='criteriasEmp.beginData!=null and criteriasEmp.beginData!= \"\"'> and hiredate &gt;= #{criteriasEmp.beginData}</if>" +
            "<if test='criteriasEmp.endData!=null and criteriasEmp.endData != \"\"'> and hiredate &lt;= #{criteriasEmp.endData}</if>" +
            "<if test='criteriasEmp.dep!=null and criteriasEmp.dep != 0'> and d.deptno=#{criteriasEmp.dep}</if>" +
            "</where></script>")
    int selectEmpCriteriasCount(@Param("criteriasEmp") CriteriasEmp criteriasEmp);
    //根据员工编号查询员工
    @Select("SELECT e.*,d.dname,d.loc FROM employee e LEFT JOIN department d ON e.deptno=d.deptno where empno=#{id}")
    @Results({
            @Result(id = true,property = "empno",column = "empno"),
            @Result(property = "ename",column = "ename"),
            @Result(property = "job",column = "job"),
            @Result(property = "hiredate",column = "hiredate"),
            @Result(property = "sal",column = "sal"),
            @Result(property = "comm",column = "comm"),
            @Result(property = "avatarURL",column = "avatarURL"),
            //部门表字段
            @Result(property = "department.deptno",column = "deptno"),
            @Result(property = "department.dname",column = "dname"),
            @Result(property = "department.loc",column = "loc")})
    Employee selectEmpById(@Param("id")int id);
    //修改员工信息
    @Update("<script>" +
            "update employee " +
            "<set>"+
            "<if test='ename != null and ename!=\"\"'> ename = #{ename},</if>"+
            "<if test='job != null and job!=\"\"'> job = #{job},</if>"+
            "<if test='hiredate != null and hiredate != \"\"'> hiredate = #{hiredate},</if>"+
            "<if test='sal != null and sal != \"\"'>sal=#{sal},</if>"+
            "<if test='comm != null and comm != \"\"'>comm=#{comm},</if>"+
            "<if test='department.deptno != null and department.deptno != \"\"'>deptno=#{department.deptno},</if>"+
            "</set>" +
            "where empno=#{empno} " +
            "</script>")
    int updateEmpById(Employee employee);
    //删除员工信息
    @Delete("<script>" +
            "delete from employee where empno in " +
            "<foreach item='id' collection='list' open='(' separator=',' close=')'>#{id}</foreach>" +
            "</script>")
    int deleteEmp(@Param("list") List<Integer> ids);
    //用户图片URl的修改
    @Update("update employee set avatarURL=#{url} where empno=#{id}")
    int UpdateEmpImageUrl(@Param("url")String avatarURL,@Param("id") int id);

    //获取用户图片路径集合
    @Select("<script> "+
            "select avatarURL from employee" +
            " where empno in "+
            "<foreach item='id' collection='list' open='(' separator=',' close=')'>#{id}</foreach>" +
            "</script>")
    List<String> selectEmpImageUrls(List<Integer> ids);

}
