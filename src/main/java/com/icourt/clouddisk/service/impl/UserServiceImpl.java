package com.icourt.clouddisk.service.impl;

import com.icourt.clouddisk.dao.IUserDao;
import com.icourt.clouddisk.entity.User;
import com.icourt.clouddisk.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户Service
 *
 * @author jianglu
 * created 2018 - 07 - 30 - TIME
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    IUserDao iUserDao;


    @Override
    public User getUser(Integer userId) {
        return iUserDao.getUser(userId);
    }


    @Override
    public User getUserByUsernameAndUserPwd(String username, String userPwd) {
        return iUserDao.getUserByUsernameAndUserPwd(username,userPwd);
    }
}
