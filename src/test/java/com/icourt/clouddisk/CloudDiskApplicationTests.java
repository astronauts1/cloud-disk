package com.icourt.clouddisk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudDiskApplicationTests {

    @Resource
    StringRedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        redisTemplate.opsForValue().set("abc","测试");
    }

}
