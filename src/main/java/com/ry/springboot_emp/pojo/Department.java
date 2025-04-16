package com.ry.springboot_emp.pojo;

/**
 * @author 21142
 * @version 1.0
 * @description: 部门实体类
 */
public class Department {
    private int deptno; //部门编号
    private String dname; //部门名称
    private String loc; //部门地址


    //构造方法
    public Department() {
    }
    public Department(int deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    //get\set方法
    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    /**
     * 重写ToString
     * @return 对象属性
     */
    @Override
    public String toString() {
        return "employee{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
