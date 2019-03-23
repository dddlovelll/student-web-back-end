package com.qmkl.service;

import com.qmkl.entity.PostLike;
import com.qmkl.entity.PostLikeResult;
import com.qmkl.entity.PostResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostLikeService {
    void addPostLike(@Param("postLike") PostLike postLike);
    void updatePostLike(@Param("userId") Integer userId,@Param("postId") Integer postId);
    boolean isLike(@Param("userId") Integer userId,@Param("postId") Integer postId);
    PostLike get(@Param("userId") Integer userId,@Param("postId") Integer postId);
    List<PostLikeResult> listLikeMe(@Param("start")Integer start,@Param("num")Integer num,@Param("postIdList")List<Integer> postIdList);

}
