package com.qmkl.service.impl;

import com.qmkl.dao.LikeDao;
import com.qmkl.entity.Like;
import com.qmkl.service.LikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("likeService")
public class LikeServiceImpl implements LikeService {

    @Resource
    private LikeDao likeDao;


    @Override
    public void addLike(Integer userId, Date voteTime, Integer fileId, boolean status) {
        likeDao.addLike(userId,voteTime,fileId,status);
    }

    @Override
    public Like getLike(Integer userId, Integer fileId) {
        return likeDao.getLike(userId,fileId);
    }

    @Override
    public void updateLikeStatus(Integer userId, Integer fileId) {
        likeDao.updateLikeStatus(userId,fileId);
    }
}
