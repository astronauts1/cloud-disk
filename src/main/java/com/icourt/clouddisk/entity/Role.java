package com.icourt.clouddisk.entity;

/**
 * 用户角色
 *
 * @author icourt
 * @create 2018 - 08 - 01 - TIME
 */
public class Role {
    private Integer roleId;

    private String roleName;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
