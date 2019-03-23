package com.qmkl.service;

import com.qmkl.entity.Post;
import com.qmkl.entity.PostResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostService {
    void addPost(Post post);
    List<PostResult> listPostWithPage(@Param("start")Integer start, @Param("num")Integer num);
    List<PostResult> listPostWithPageWithClassify(@Param("start")Integer start, @Param("num")Integer num,@Param("classify") Integer classify);
    List<PostResult> listPostWithPageByUserId(@Param("start")Integer start,@Param("num")Integer num,@Param("userId")Integer userId);
    void updatePostCommentNum(Integer postId);
    void updatePostAddLikeNum(Integer postId);
    void updatePostDescLikeNum(Integer postId);
    PostResult getById(Integer postId);
    List<Integer> getAllPostIdByUserId(Integer userId);
    void delById(Integer postId);
    void updatePostAddReportNum(Integer postId);
}
