package com.qmkl.dao;

import com.qmkl.entity.MyCollect;
import com.qmkl.entity.MyCollectResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyCollectDao {
    void add(@Param("myCollect") MyCollect myCollect);
    MyCollect get(@Param("userId") Integer userId,@Param("postId")Integer postId);
    void update(@Param("userId") Integer userId,@Param("postId")Integer postId,@Param("state")boolean state);
    List<MyCollectResult> listMyCollectWithPage(@Param("start")Integer start, @Param("num")Integer num, @Param("userId")Integer userId);
}
