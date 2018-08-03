package com.icourt.clouddisk.exception;

/**
 * 角色未经过授权
 *
 * @author jianglu
 * Created 2018 - 08 - 03 - TIME
 */
public class UnRoleAuthorizedException extends RuntimeException{
    public UnRoleAuthorizedException(String message) {
        super(message);
    }

    public UnRoleAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnRoleAuthorizedException(Throwable cause) {
        super(cause);
    }
}
