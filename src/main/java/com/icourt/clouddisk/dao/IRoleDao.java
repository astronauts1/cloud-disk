package com.icourt.clouddisk.dao;

import com.icourt.clouddisk.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色dao
 *
 * @author icourt
 * @create 2018 - 08 - 01 - TIME
 */
@Mapper
public interface IRoleDao {

    /**
     * 根据用户id获取该用户所有的角色
     * @param userId
     * @return
     */
    List<Role> listRoleByUserId(@Param("userId") Integer userId);
}
