package com.icourt.clouddisk.service;

import com.icourt.clouddisk.entity.Role;

import java.util.List;

public interface IRoleService {

    /**
     * 根据用户id获取该用户所有的角色
     * @param userId
     * @return
     */
    List<Role> listRoleByUserId(Integer userId);
}
