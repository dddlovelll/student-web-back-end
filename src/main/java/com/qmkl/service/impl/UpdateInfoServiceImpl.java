package com.qmkl.service.impl;

import com.qmkl.dao.UpdateInfoDao;
import com.qmkl.entity.UpdateInfo;
import com.qmkl.service.UpdateInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("updateInfoService")
public class UpdateInfoServiceImpl implements UpdateInfoService {

    @Resource
    private UpdateInfoDao updateInfoDao;


    @Override
    public UpdateInfo getLastUpdateInfo() {
        return updateInfoDao.getLastUpdateInfo();
    }
}
