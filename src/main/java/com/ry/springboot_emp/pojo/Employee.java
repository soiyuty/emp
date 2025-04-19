package com.ry.springboot_emp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author 21142
 * @version 1.0
 * @description: 员工实体类
 */
public class Employee {
    @TableId(value = "empno",type = IdType.AUTO)
    private int empno;
    private String ename;
    private String job;
    private String hiredate;
    private Double sal;
    @TableField("comm")
    private Double comm;
    @TableField(exist = false)
    private Department department;
    @TableField("deptno")
    private int deptno;
    @TableField(value = "avatarURL")
    private String avatarURL;


    //构造方法
    public Employee() {
    }



    //get\set
    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public Employee(int empno, String ename, String job, String hiredate, Double sal, Double comm, Department department, int deptno, String avatarURL) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.department = department;
        this.deptno = deptno;
        this.avatarURL = avatarURL;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    /**
     * 重写ToString
     *
     * @return 对象属性
     */
    @Override
    public String toString() {
        return "Employee{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", hiredate='" + hiredate + '\'' +
                ", sal=" + sal +
                ", comm=" + comm +
                ", department=" + department +
                ", deptno=" + deptno +
                ", avatarURL='" + avatarURL + '\'' +
                '}';
    }

}
