package com.qmkl.service.impl;

import com.qmkl.dao.AcademyDao;
import com.qmkl.dao.AdvertDao;
import com.qmkl.entity.Advert;
import com.qmkl.service.AdvertService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("advertService")
public class AdvertServiceImpl implements AdvertService {

    @Resource
    private AdvertDao advertDao;


    @Override
    public Advert getAdvertByRemark(String remark) {
        return advertDao.getAdvertByRemark(remark);
    }

    @Override
    public List<Advert> getAdvertByRemarkList(String remark) {
        return advertDao.getAdvertByRemarkList(remark);
    }

    @Override
    public void saveAdDate(String ip, Date createdAt, String type, String remark) {
        advertDao.saveAdDate(ip,createdAt,type,remark);
    }
}
