package com.icourt.clouddisk.service.impl;

import com.icourt.clouddisk.dao.IRoleDao;
import com.icourt.clouddisk.entity.Role;
import com.icourt.clouddisk.service.IRoleService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 角色service实现类
 *
 * @author icourt
 * @create 2018 - 08 - 01 - TIME
 */

@Service
public class RoleServiceImpl implements IRoleService {


    @Resource
    IRoleDao roleDao;

    @Override
    public List<Role> listRoleByUserId(Integer userId) {
        return roleDao.listRoleByUserId(userId);
    }

    @Cacheable("listRoleNameByUserId")
    @Override
    public Set<String> listRoleNameByUserId(Integer userId) {
        return roleDao.listRoleNameByUserId(userId);
    }
}
