package com.icourt.clouddisk.entity;

/**
 * 用户权限
 *
 * @author icourt
 * @create 2018 - 08 - 01 - TIME
 */
public class Permission {
    private Integer permissionId;

    private String permissionName;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
