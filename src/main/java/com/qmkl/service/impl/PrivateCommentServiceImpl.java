package com.qmkl.service.impl;

import com.qmkl.dao.PrivateCommentDao;
import com.qmkl.entity.Comment;
import com.qmkl.entity.PrivateComment;
import com.qmkl.entity.PrivateCommentResult;
import com.qmkl.service.PrivateCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("privateCommentService")
public class PrivateCommentServiceImpl implements PrivateCommentService {

    @Resource
    private PrivateCommentDao privateCommentDao;


    @Override
    public void addComment(PrivateComment privateComment) {
        privateCommentDao.addComment(privateComment);
    }

    @Override
    public List<PrivateCommentResult> getList(Integer userId, Integer postId) {
        return privateCommentDao.getList(userId, postId);
    }
}
