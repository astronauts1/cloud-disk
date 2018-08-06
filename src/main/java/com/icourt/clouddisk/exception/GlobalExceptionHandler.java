package com.icourt.clouddisk.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author jianglu
 * Created 2018 - 08 - 02 - TIME
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UnAuthorizedException.class)
    public ResponseEntity<String> unAuthorizedExceptionHandler(UnAuthorizedException e){
        log.error("捕获未认证异常",e);
        return ResponseEntity.ok("用户未认证");
    }


    @ExceptionHandler(value = UnRoleAuthorizedException.class)
    public ResponseEntity<String> unRoleAuthorized(UnRoleAuthorizedException e){
        log.error("捕获用户不具有合法角色异常",e);
        return ResponseEntity.ok("用户不具有合法角色");
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> unException(Exception e){
        log.error("捕获异常信息",e);
        return ResponseEntity.ok("系统发生了异常，请联系管理员");
    }

}
