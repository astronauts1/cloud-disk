package com.icourt.clouddisk.controller;

import com.icourt.clouddisk.entity.User;
import com.icourt.clouddisk.service.IUserService;
import com.icourt.clouddisk.utils.JwtUtil;
import com.nimbusds.jose.JOSEException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户相关逻辑
 *
 * @author jianglu
 * Created 2018 - 08 - 02 - TIME
 */
@Slf4j
@Api(value = "demo api")
@RestController
public class UserController {

    @Resource
    IUserService iUserService;


    @ApiOperation(value = "用户登录", notes = "根据用户输入的用户名密码对用户进行验证")
    @PostMapping("/login")
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
            payloadMap.put("exp", currentTime + 1000 * 60*10);
            try {
                String token = JwtUtil.createToken(payloadMap);
                results.put(JwtUtil.TOKEN, token);
            } catch (JOSEException e) {
                e.printStackTrace();
            }
        }
        else{
            results.put("error","用户名或密码输入错误");
        }
        return ResponseEntity.ok(results);
    }
}
