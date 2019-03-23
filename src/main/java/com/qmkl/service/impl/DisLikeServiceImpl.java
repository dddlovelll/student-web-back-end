package com.qmkl.service.impl;

import com.qmkl.dao.DisLikeDao;
import com.qmkl.dao.LikeDao;
import com.qmkl.entity.Like;
import com.qmkl.service.DisLikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("disLikeService")
public class DisLikeServiceImpl implements DisLikeService {

    @Resource
    private DisLikeDao disLikeDao;

    @Override
    public void addDisLike(Integer userId, Date voteTime, Integer fileId, boolean status) {
        disLikeDao.addDisLike(userId,voteTime,fileId,status);
    }

    @Override
    public Like getDisLike(Integer userId, Integer fileId) {
        return disLikeDao.getDisLike(userId,fileId);
    }

    @Override
    public void updateDisLikeStatus(Integer userId, Integer fileId) {
        disLikeDao.updateDisLikeStatus(userId,fileId);
    }
}
