package com.icourt.clouddisk.service;

import com.icourt.clouddisk.entity.Role;

import java.util.List;
import java.util.Set;

public interface IRoleService {

    /**
     * 根据用户id获取该用户所有的角色
     * @param userId
     * @return
     */
    List<Role> listRoleByUserId(Integer userId);

    /**
     * 获取用户id对应的所有的角色名称
     * @param userId
     * @return
     */
    Set<String> listRoleNameByUserId(Integer userId);
}
