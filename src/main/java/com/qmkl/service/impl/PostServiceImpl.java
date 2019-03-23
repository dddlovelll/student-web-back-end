package com.qmkl.service.impl;

import com.qmkl.dao.PostDao;
import com.qmkl.entity.Post;
import com.qmkl.entity.PostResult;
import com.qmkl.service.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {

    @Resource
    private PostDao postDao;


    @Override
    public void addPost(Post post) {
        postDao.addPost(post);
    }

    @Override
    public List<PostResult> listPostWithPage(Integer start, Integer num) {
        return postDao.listPostWithPage(start,num);
    }

    @Override
    public List<PostResult> listPostWithPageWithClassify(Integer start, Integer num, Integer classify) {
        return postDao.listPostWithPageWithClassify(start, num, classify);
    }


    @Override
    public List<PostResult> listPostWithPageByUserId(Integer start, Integer num, Integer userId) {
        return postDao.listPostWithPageByUserId(start, num, userId);
    }

    @Override
    public void updatePostCommentNum(Integer postId) {
        postDao.updatePostCommentNum(postId);
    }

    @Override
    public void updatePostAddLikeNum(Integer postId) {
        postDao.updatePostAddLikeNum(postId);
    }

    @Override
    public void updatePostDescLikeNum(Integer postId) {
        postDao.updatePostDescLikeNum(postId);
    }

    @Override
    public PostResult getById(Integer postId) {
        return postDao.getById(postId);
    }

    @Override
    public List<Integer> getAllPostIdByUserId(Integer userId) {
        return postDao.getAllPostIdByUserId(userId);
    }

    @Override
    public void delById(Integer postId) {
        postDao.delById(postId);
    }

    @Override
    public void updatePostAddReportNum(Integer postId) {
        postDao.updatePostAddReportNum(postId);
    }
}
