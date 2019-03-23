package com.qmkl.service.impl;

import com.qmkl.dao.CollegeDao;
import com.qmkl.entity.College;
import com.qmkl.service.CollegeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("collegeService")
public class CollegeServiceImpl implements CollegeService {
    @Resource
    private CollegeDao collegeDao;

    @Override
    public String selectCollegeNameById(Integer id) {
        return collegeDao.selectCollegeNameById(id);
    }

    @Cacheable(value = "selectIdByCollegeName",key = "#collegeName")
    @Override
    public Integer selectIdByCollegeName(String collegeName) {
        return collegeDao.selectIdByCollegeName(collegeName);
    }

    @Override
    public List<String> listAllName() {
        return collegeDao.listAllName();
    }

    @Override
    public College selectCollegeById(Integer id) {
        return collegeDao.selectCollegeById(id);
    }

    @Override
    public void insertCollege(College college) {
        collegeDao.insertCollege(college);
    }

    @Override
    public void updateCollege(College college) {
        collegeDao.updateCollege(college);
    }
}
