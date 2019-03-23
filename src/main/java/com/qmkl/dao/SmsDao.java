package com.qmkl.dao;

import com.qmkl.entity.Sms;
import org.apache.ibatis.annotations.Param;

public interface SmsDao {
    void insertSms(Sms sms);//插入sms
    String selectSmsTokenCodeByPhoneAndVercode(@Param("phone")String phone,@Param("vercode")String vercode);//通过手机号和验证码查找token
    void updateSmsTokenCodeByPhoneAndVercode(@Param("phone")String phone,@Param("vercode")String vercode);//通过手机号和验证码更新token使之失效
}
