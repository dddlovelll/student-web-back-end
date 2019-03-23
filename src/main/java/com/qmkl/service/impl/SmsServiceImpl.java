package com.qmkl.service.impl;

import com.qmkl.dao.SmsDao;
import com.qmkl.entity.Sms;
import com.qmkl.service.SmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("smsService")
public class SmsServiceImpl implements SmsService {
    @Resource
    private SmsDao smsDao;

    @Override
    public void insertSms(Sms sms) {
        smsDao.insertSms(sms);
    }

    @Override
    public void updateSmsTokenCodeByPhoneAndVercode(String phone, String vercode) {
        smsDao.updateSmsTokenCodeByPhoneAndVercode(phone,vercode);
    }

    @Override
    public String selectSmsTokenCodeByPhoneAndVercode(String phone, String vercode) {
        return smsDao.selectSmsTokenCodeByPhoneAndVercode(phone,vercode);
    }


}
