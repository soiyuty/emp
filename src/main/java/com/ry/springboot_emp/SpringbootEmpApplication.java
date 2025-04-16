package com.ry.springboot_emp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ry.springboot_emp.mapper")
public class SpringbootEmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEmpApplication.class, args);
    }

}
