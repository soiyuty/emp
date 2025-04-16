package com.ry.springboot_emp.exception;

/**
 * @author 21142
 * @version 1.0
 * @description: 系统异常自定义异常类
 */
public class SystemException extends RuntimeException {
    private Integer code; //异常编号

    public Integer getCode() {
        return code;
    }

    //构造方法
    public SystemException(Integer code) {
        this.code = code;
    }

    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
