package com.qmkl.service.impl;

import com.qmkl.dao.CommentDao;
import com.qmkl.entity.Comment;
import com.qmkl.entity.CommentResult;
import com.qmkl.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    @Override
    public void addComment(Comment comment) {
        commentDao.addComment(comment);
    }

    @Override
    public List<CommentResult> listCommentWithPage(Integer start, Integer num, Integer postId) {
        return commentDao.listCommentWithPage(start,num,postId);
    }

    @Override
    public List<CommentResult> listCommentWithPageByUserId(Integer start, Integer num, Integer userId) {
        return commentDao.listCommentWithPageByUserId(start, num, userId);
    }
}
