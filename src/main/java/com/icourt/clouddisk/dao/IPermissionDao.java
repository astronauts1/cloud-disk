package com.icourt.clouddisk.dao;

import com.icourt.clouddisk.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IPermissionDao {


    /**
     * 根据角色id查询权限
     * @param roleId
     * @return
     */
    List<Permission> listPermissionByRoleId(@Param("roleId") Integer roleId);

}
