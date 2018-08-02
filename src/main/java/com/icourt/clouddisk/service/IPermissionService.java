package com.icourt.clouddisk.service;




import com.icourt.clouddisk.entity.Permission;

import java.util.List;

public interface IPermissionService {

    /**
     * 根据角色id查询权限
     * @param roleId
     * @return
     */
    List<Permission> listPermissionByRoleId(Integer roleId);
}
