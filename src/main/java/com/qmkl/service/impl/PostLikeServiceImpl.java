package com.qmkl.service.impl;

import com.qmkl.dao.PostLikeDao;
import com.qmkl.entity.PostLike;
import com.qmkl.entity.PostLikeResult;
import com.qmkl.entity.PostResult;
import com.qmkl.service.PostLikeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("postLikeService")
public class PostLikeServiceImpl implements PostLikeService {


    @Resource
    private PostLikeDao postLikeDao;

    @Override
    public void addPostLike(PostLike postLike) {
        postLikeDao.addPostLike(postLike);
    }

    @Override
    public void updatePostLike(Integer userId, Integer postId) {
        postLikeDao.updatePostLike(userId,postId);
    }

    @Override
    public boolean isLike(Integer userId, Integer postId) {
        return postLikeDao.isLike(userId,postId);
    }

    @Override
    public PostLike get(Integer userId, Integer postId) {
        return postLikeDao.get(userId,postId);
    }

    @Override
    public List<PostLikeResult> listLikeMe(Integer start, Integer num, List<Integer> postIdList) {
        return postLikeDao.listLikeMe(start, num, postIdList);
    }


}
