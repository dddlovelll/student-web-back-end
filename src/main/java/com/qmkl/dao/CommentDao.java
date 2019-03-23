package com.qmkl.dao;

import com.qmkl.entity.Comment;
import com.qmkl.entity.CommentResult;
import com.qmkl.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDao {
    void addComment(@Param("comment") Comment comment);
    List<CommentResult> listCommentWithPage(@Param("start")Integer start, @Param("num")Integer num, @Param("postId")Integer postId);
    List<CommentResult> listCommentWithPageByUserId(@Param("start")Integer start,@Param("num")Integer num,@Param("userId")Integer userId);
}
