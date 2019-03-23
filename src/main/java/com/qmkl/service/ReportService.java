package com.qmkl.service;

import com.qmkl.entity.Report;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportService {
    void addReport(@Param("report") Report report);
    Report get(@Param("userId") Integer userId,@Param("postId") Integer postId);
}
