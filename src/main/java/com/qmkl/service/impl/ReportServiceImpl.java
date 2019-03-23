package com.qmkl.service.impl;

import com.qmkl.dao.ReportDao;
import com.qmkl.entity.Report;
import com.qmkl.service.ReportService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("reportService")
public class ReportServiceImpl implements ReportService {


    @Resource
    private ReportDao reportDao;



    @Override
    public void addReport(Report report) {
        reportDao.addReport(report);
    }


    @Override
    public Report get(Integer userId, Integer postId) {
        return reportDao.get(userId, postId);
    }


}
