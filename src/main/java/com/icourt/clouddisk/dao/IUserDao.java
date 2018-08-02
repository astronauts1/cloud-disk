package com.icourt.clouddisk.dao;

import com.icourt.clouddisk.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author jianglu
 */
@Mapper
public interface IUserDao {


    /**
     * 根据用户id查询用户
     * @param userId 用户id
     * @return 查询到的用户结果
     */
    User getUser(@Param("userId") Integer userId);

    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param userPwd 密码
     * @return 用户
     */
    User getUserByUsernameAndUserPwd(@Param("username") String username, @Param("userPwd") String userPwd);
}
