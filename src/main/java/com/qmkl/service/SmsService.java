package com.qmkl.service;

import com.qmkl.entity.Sms;
import org.apache.ibatis.annotations.Param;

public interface SmsService {
    void insertSms(Sms sms);//插入sms
    void updateSmsTokenCodeByPhoneAndVercode(@Param("phone")String phone,@Param("vercode")String vercode);//通过手机号和验证码更新token使之失效
    String selectSmsTokenCodeByPhoneAndVercode(@Param("phone")String phone,@Param("vercode")String vercode);//通过手机号和验证码查找token
}
