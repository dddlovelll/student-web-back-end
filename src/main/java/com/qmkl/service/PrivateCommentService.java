package com.qmkl.service;

import com.qmkl.entity.Comment;
import com.qmkl.entity.PrivateComment;
import com.qmkl.entity.PrivateCommentResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrivateCommentService {
    void addComment(@Param("comment") PrivateComment privateComment);
    List<PrivateCommentResult> getList(@Param("userId") Integer userId, @Param("postId") Integer postId);
}
