package com.icourt.clouddisk.controller;

import com.icourt.clouddisk.entity.User;
import com.icourt.clouddisk.exception.UnAuthorizedException;
import com.icourt.clouddisk.service.IUserService;
import com.icourt.clouddisk.utils.JwtUtil;
import com.nimbusds.jose.JOSEException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


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


    @ApiOperation(value = "用户登录", notes = "根据用户输入的用户名密码对用户进行验证")
    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {


        Map<String, Object> results = new HashMap<>(4);
        if (user == null) {
            results.put("error", "请输入合法的信息");
            return ResponseEntity.ok(results);
        }
        boolean userInputIsValid = StringUtils.isNotBlank(user.getUserName())
                && StringUtils.isNotBlank(user.getUserPwd());
        if (!userInputIsValid) {
            results.put("error", "请输入合法的信息");
            return ResponseEntity.ok(results);
        }
        //登录
        User userReal = iUserService.getUserByUsernameAndUserPwd(user.getUserName(), user.getUserPwd());
        if (userReal != null) {
            results.put("ok", "登录成功");
            //生成token
            //存入载荷
            Map<String, Object> payloadMap = new HashMap<>(5);
            payloadMap.put("uid", userReal.getUserId());
            Long currentTime = System.currentTimeMillis();
            payloadMap.put("sta", currentTime);
            payloadMap.put("exp", currentTime + 1000 * 60);
            try {
                String token = JwtUtil.createToken(payloadMap);
                results.put(JwtUtil.TOKEN, token);
            } catch (JOSEException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok(results);
    }

    @ApiOperation(value = "测试全局异常的",notes = "用来测试全局异常是否起作用了")
    @GetMapping("/test")
    public ResponseEntity<String> exceptionTest()throws UnAuthorizedException{
        throw new UnAuthorizedException("用户未认证");

    }

}
