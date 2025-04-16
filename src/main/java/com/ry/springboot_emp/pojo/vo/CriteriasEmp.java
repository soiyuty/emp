package com.ry.springboot_emp.pojo.vo;

/**
 * @author 21142
 * @version 1.0
 * @description: 员工多条件查询属性类
 */
public class CriteriasEmp {
    private String name;
    private String job;
    private String beginData;
    private String endData;
    private int dep;

    public CriteriasEmp() {
    }

    public CriteriasEmp(String name, String job, String beginData, String endData, int dep) {
        this.name = name;
        this.job = job;
        this.beginData = beginData;
        this.endData = endData;
        this.dep = dep;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBeginData() {
        return beginData;
    }

    public void setBeginData(String beginData) {
        this.beginData = beginData;
    }

    public String getEndData() {
        return endData;
    }

    public void setEndData(String endData) {
        this.endData = endData;
    }

    public int getDep() {
        return dep;
    }

    public void setDep(int dep) {
        this.dep = dep;
    }

    @Override
    public String toString() {
        return "CriteriasEmp{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", beginData='" + beginData + '\'' +
                ", endData='" + endData + '\'' +
                ", dep=" + dep +
                '}';
    }
}
