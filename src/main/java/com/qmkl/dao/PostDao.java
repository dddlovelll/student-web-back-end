package com.qmkl.dao;

import com.qmkl.entity.Post;
import com.qmkl.entity.PostResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostDao {
    void addPost(@Param("post") Post post);
    void updatePostCommentNum(Integer postId);
    void updatePostAddLikeNum(Integer postId);
    void updatePostDescLikeNum(Integer postId);
    void updatePostAddReportNum(Integer postId);
    void delById(Integer postId);
    PostResult getById(Integer postId);
    List<Integer> getAllPostIdByUserId(Integer userId);
    List<PostResult> listPostWithPage(@Param("start")Integer start, @Param("num")Integer num);
    List<PostResult> listPostWithPageWithClassify(@Param("start")Integer start, @Param("num")Integer num,@Param("classify") Integer classify);
    List<PostResult> listPostWithPageByUserId(@Param("start")Integer start,@Param("num")Integer num,@Param("userId")Integer userId);
}
