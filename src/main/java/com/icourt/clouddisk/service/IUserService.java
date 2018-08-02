package com.icourt.clouddisk.service;


import com.icourt.clouddisk.entity.User;

/**
 * @author jianglu
 */
public interface IUserService {
    /**
     * 根据用户id查询到用户
     * @param userId 用户id
     * @return 用户对象
     */
    User getUser(Integer userId);

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param userPwd
     * @return
     */
    User getUserByUsernameAndUserPwd(String username, String userPwd);
}
