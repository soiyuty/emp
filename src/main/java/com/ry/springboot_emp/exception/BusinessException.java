package com.ry.springboot_emp.exception;

/**
 * @author 21142
 * @version 1.0
 * @description: 业务异常自定义异常类
 */
public class BusinessException extends RuntimeException {
    private Integer code; //异常编号

    public Integer getCode() {
        return code;
    }

    //构造方法
    public BusinessException(Integer code) {
        this.code = code;
    }

    public BusinessException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Integer code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }


}
