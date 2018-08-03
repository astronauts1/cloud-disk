package com.icourt.clouddisk.controller;

import com.icourt.clouddisk.annotation.RequiredRole;
import com.icourt.clouddisk.entity.User;
import com.icourt.clouddisk.exception.UnAuthorizedException;
import com.icourt.clouddisk.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * api demo
 *
 * @author icourt
 * created 2018 - 07 - 28 - TIME
 */
@Slf4j
@Api(value = "demo api")
@RestController
@RequestMapping("/api/v1/")
public class DemoController {


    @Resource
    IUserService iUserService;

    /**
     * 获取信息
     *
     * @param userId 用户的id
     * @return 用户信息
     */
    @ApiOperation(value = "获取用户详细信息", notes = "根据用户的url中的id来获取他的详细信息")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Integer", paramType = "path")
    @GetMapping("user/{userId}")
    public ResponseEntity<User> getUsersById(@PathVariable(value = "userId") Integer userId) {
        User user = iUserService.getUser(userId);
        log.debug("log test");
        return ResponseEntity.ok(user);
    }




    @GetMapping("/zip")
    @ApiOperation(value = "测试用户角色注解功能是否可用",notes = "用来测试用户角色注解")
    @RequiredRole("vip")
    public ResponseEntity<String> testRole(){
        return ResponseEntity.ok("ok");
    }


    @ApiOperation(value = "测试全局异常的",notes = "用来测试全局异常是否起作用了")
    @GetMapping("/test")
    public ResponseEntity<String> exceptionTest()throws UnAuthorizedException{
        throw new UnAuthorizedException("用户未认证");

    }

}
