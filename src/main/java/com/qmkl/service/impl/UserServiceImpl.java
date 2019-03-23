package com.qmkl.service.impl;
import com.qmkl.dao.UserDao;
import com.qmkl.entity.User;
import com.qmkl.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userDao.getUserByUsernameAndPassword(username,password);
    }

    @Override
    public User getUserByToken(String token) {
        return userDao.getUserByToken(token);
    }

    @Override
    public User selectUserById(Integer id) {
        return userDao.selectUserById(id);
    }

    @Override
    public User selectUserByUsername(String username) {
      //  System.out.println("查询数据库");
        return userDao.selectUserByUsername(username);
    }

    @Override
    public User selectUserByNickname(String nickname) {
        return userDao.selectUserByNickname(nickname);
    }

    @Override
    public User selectUserByPhone(String phone) {
        return userDao.selectUserByPhone(phone);
    }

    @Override
    public Integer selectUserByUsernameAndPassword(String username, String password) {
        return userDao.selectUserByUsernameAndPassword(username,password);
    }

    @Override
    public String selectTokenByUsername(String username) {
        return userDao.selectTokenByUsername(username);
    }

    @Override
    public void updateTokenByUsername(String token, String username) {
        userDao.updateTokenByUsername(token,username);
    }

    @Override
    public void insertUserByRegister(String username, String password, String phone, Date createdAtd, Date updatedAt) {
        userDao.insertUserByRegister(username,password,phone,createdAtd,updatedAt);
    }


    @Override
    public void insertUser(User user, Integer collegeId, Integer academyId) {
        userDao.insertUser(user,collegeId,academyId);
    }

    @Override
    public Integer getUserIdByToken(String token) {
        return userDao.getUserIdByToken(token);
    }

    @Override
    public String getNickNameById(Integer id) {
        return userDao.getNickNameById(id);
    }

    @Override
    public void updateTokenById(Integer id, String token) {
        userDao.updateTokenById(id,token);
    }

    @Override
    public void updateUser(User user, Integer collegeId, Integer academyId) {
        userDao.updateUser(user,collegeId,academyId);
    }

    @Override
    public void updateTimeByUserName(String username, Date updatedAt) {
        userDao.updateTimeByUserName(username,updatedAt);
    }

    @Override
    public void updateUserPasswordByUserName(String username, String password) {
        userDao.updateUserPasswordByUserName(username,password);
    }

    @Override
    public String getUsernameByToken(String token) {
        return userDao.getUsernameByToken(token);
    }

    @Override
    public String getUsernameById(Integer id) {
        return userDao.getUsernameById(id);
    }

    @Override
    public void updateAvatar(String username, String avatar) {
        userDao.updateAvatar(username,avatar);
    }

    @Override
    public String getAvatar(String username) {
        return userDao.getAvatar(username);
    }


}
