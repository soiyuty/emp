package com.ry.springboot_emp.controller.resouls;

/**
 * @author 21142
 * @version 1.0
 * @description: 响应对象类
 */
public class Result {
    private Object data;
    private Integer code;
    private String msg;


    //构造
    public Result() {
    }
    public Result(Integer code,Object data , String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
    public Result(Integer code,Object data ) {
        this.data = data;
        this.code = code;
    }
    //get\set方法
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
