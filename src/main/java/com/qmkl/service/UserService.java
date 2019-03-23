package com.qmkl.service;

import com.qmkl.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserService {
    User getUserByUsernameAndPassword(@Param("username")String username, @Param("password")String password);
    User getUserByToken(String token); // 通过token查询用户
    User selectUserById(Integer id);//通过id查找用户
    User selectUserByUsername(String username);//通过用户名查找用户
    User selectUserByNickname(String nickname);//通过昵称查找用户
    User selectUserByPhone(String phone);//通过手机号查找用户
    Integer selectUserByUsernameAndPassword(String username,String password);//通过账号密码查找用户
    String selectTokenByUsername(String username);//通过username查询token
    void updateTokenByUsername(@Param("token")String token,@Param("username")String username);//通过username更新token
    void insertUserByRegister(@Param("username") String username,@Param("password")String password,@Param("phone")String phone,@Param("createdAtd") Date createdAtd,@Param("updatedAt") Date updatedAt);//插入用户
    void updateUser(User user,Integer collegeId,Integer academyId);//更新用户
    void updateTimeByUserName(@Param("username")String username,@Param("updatedAt")Date updatedAt);//用户登录后更新一下时间
    void updateUserPasswordByUserName(@Param("username")String username,@Param("password") String password);//通过username修改密码
    String getUsernameByToken(String token);  // 通过token得到用户名
    String getUsernameById(Integer id);  // 通过id得到用户名
    void updateAvatar(@Param("username")String username,@Param("avatar") String avatar); // 更新用户头像
    String getAvatar(String username); // 获取用户头像路径
    void insertUser(User user,Integer collegeId,Integer academyId);//插入用户
    Integer getUserIdByToken(String token);  // 通过token得到用户id
    String getNickNameById(Integer id);
    void updateTokenById(@Param("id")Integer id, @Param("token") String token); // 通过id修改token
}