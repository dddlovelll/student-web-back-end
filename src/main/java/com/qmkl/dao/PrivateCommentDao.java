package com.qmkl.dao;
import com.qmkl.entity.PrivateComment;
import com.qmkl.entity.PrivateCommentResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PrivateCommentDao {
    void addComment(@Param("comment") PrivateComment comment);
    List<PrivateCommentResult> getList(@Param("userId") Integer userId, @Param("postId") Integer postId);
}
