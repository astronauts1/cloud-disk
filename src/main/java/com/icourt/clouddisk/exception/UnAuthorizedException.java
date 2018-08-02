package com.icourt.clouddisk.exception;

/**
 * 用户未认证异常
 * @author jianglu
 * @create 2018 - 08 - 02 - TIME
 */
public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException() {
        super();
    }

    public UnAuthorizedException(String message) {
        super(message);
    }

    public UnAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
