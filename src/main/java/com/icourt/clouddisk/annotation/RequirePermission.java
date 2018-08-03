package com.icourt.clouddisk.annotation;

import com.icourt.clouddisk.common.Logical;

/**
 * 需要权限注解
 *
 * @author jianglu
 * Created 2018 - 08 - 02 - TIME
 */
public @interface RequirePermission {
    String[] value();

    Logical logical() default Logical.OR;
}
