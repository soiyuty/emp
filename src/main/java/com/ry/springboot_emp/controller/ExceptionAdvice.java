package com.ry.springboot_emp.controller;

import com.ry.springboot_emp.controller.resouls.Result;
import com.ry.springboot_emp.exception.BusinessException;
import com.ry.springboot_emp.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 21142
 * @version 1.0
 * @description: 处理异常类
 */
@RestControllerAdvice
public class ExceptionAdvice {
//    所有异常
//    @ExceptionHandler(Exception.class)
//    public Result doException(Exception e){
//        return new Result(Code.ERR,0,"服务器错误，用户自己解决");
//    }
    //业务异常
    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException e){
        return new Result(e.getCode(),null,e.getMessage());
    }

    //系统异常
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException e){
        return new Result(e.getCode(),null,e.getMessage());
    }
}
