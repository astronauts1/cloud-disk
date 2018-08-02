package com.icourt.clouddisk.service.impl;


import com.icourt.clouddisk.dao.IPermissionDao;
import com.icourt.clouddisk.entity.Permission;
import com.icourt.clouddisk.service.IPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限service实现类
 *
 * @author icourt
 * @create 2018 - 08 - 01 - TIME
 */

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Resource
    IPermissionDao permissionDao;

    @Override
    public List<Permission> listPermissionByRoleId(Integer roleId) {
        return permissionDao.listPermissionByRoleId(roleId);
    }
}
