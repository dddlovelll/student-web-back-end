package com.qmkl.dao;


import com.qmkl.entity.Report;
import org.apache.ibatis.annotations.Param;


public interface ReportDao {
    void addReport(@Param("report") Report report);
    Report get(@Param("userId") Integer userId,@Param("postId") Integer postId);
}
