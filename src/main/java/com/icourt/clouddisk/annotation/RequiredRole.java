package com.icourt.clouddisk.annotation;


import com.icourt.clouddisk.common.Logical;

import java.lang.annotation.*;


/**
 * 身份认证注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RequiredRole {

    String[] value();

    Logical logical() default Logical.OR;
}
