package com.qmkl.service;

import com.qmkl.entity.Like;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface LikeService {
    void addLike(@Param("userId")Integer userId, @Param("voteTime")Date voteTime, @Param("fileId")Integer fileId, @Param("status")boolean status); // 添加记录
    Like getLike(@Param("userId")Integer userId,@Param("fileId")Integer fileId); // 获取记录
    void updateLikeStatus(@Param("userId")Integer userId,@Param("fileId")Integer fileId); // 更新状态
}
