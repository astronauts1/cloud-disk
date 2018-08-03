package com.icourt.clouddisk;

import com.icourt.clouddisk.service.IRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class CloudDiskApplicationTests {


    @Resource
    RedisTemplate redisTemplate;

    @Resource
    IRoleService roleService;

    @Test
    public void contextLoads() {
        roleService.listRoleNameByUserId(1);

        roleService.listRoleNameByUserId(1);
        roleService.listRoleNameByUserId(1);
    }

}
