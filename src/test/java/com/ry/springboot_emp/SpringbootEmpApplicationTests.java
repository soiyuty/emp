package com.ry.springboot_emp;
import com.ry.springboot_emp.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbootEmpApplicationTests {

@Autowired
RedisTemplate<String,Object> redisTemplate;
@Autowired
    private DepartmentService departmentService;
    @Test
    public void t1(){
//        redisTemplate.opsForValue().set("hh","dawdwa",20, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("hh"));
    }
    @Test
    public void t2(){
        departmentService.getDepCountByDepId(10);
    }
}
