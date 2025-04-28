package com.ry.springboot_emp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ry.springboot_emp.mapper")
public class SpringbootEmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEmpApplication.class, args);
        System.out.println(
                "\n" +
                        ""+
                        "   佛曰:\n" +
                        "          写字楼里写字间，写字间里程序员；\n" +
                        "          程序人员写程序，又拿程序换酒钱。\n" +
                        "          酒醒只在网上坐，酒醉还来网下眠；\n" +
                        "          酒醉酒醒日复日，网上网下年复年。\n" +
                        "          但愿老死电脑间，不愿鞠躬老板前；\n" +
                        "          奔驰宝马贵者趣，公交自行程序员。\n" +
                        "          别人笑我忒疯癫，我笑自己命太贱；\n" +
                        "          不见满街漂亮妹，哪个归得程序员？\n" +
                        " 启动成功了,快点写吧牛马"
        );
    }

}
