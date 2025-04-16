package com.ry.springboot_emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbootEmpApplicationTests {

@Autowired
RedisTemplate<String,Object> redisTemplate;
    @Test
    public void t1(){
//        redisTemplate.opsForValue().set("hh","dawdwa",20, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("hh"));
    }
}
