package com.qmkl.service.impl;
import com.qmkl.dao.AcademyDao;
import com.qmkl.entity.Academy;
import com.qmkl.service.AcademyService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("academyService")
public class AcademyServiceImpl implements AcademyService {

    @Resource
    private AcademyDao academyDao;

    @Override
    public String selectAcademyNameById(Integer id) {
        return academyDao.selectAcademyNameById(id);
    }

    @Override
    public Integer selectIdByAcademyName(String academyName) {
        return academyDao.selectIdByAcademyName(academyName);
    }

    @Override
    public List<String> selectAllAcademyName() {
        return academyDao.selectAllAcademyName();
    }


    @Cacheable(value = "selectAllAcademyNameByCollegeName",key = "#collegeId")
    @Override
    public List<String> selectAllAcademyNameByCollegeName(Integer collegeId) {
        return academyDao.selectAllAcademyNameByCollegeName(collegeId);
    }

    @Override
    public Integer getIdByAcademyNameAndCollegeId(String academyName, Integer collegeId) {
        return academyDao.getIdByAcademyNameAndCollegeId(academyName,collegeId);
    }


    @Override
    public Academy selectAcademyById(Integer id) {
        return academyDao.selectAcademyById(id);
    }

    @Override
    public void insertAcademy(Academy academy, Integer collegeId) {
        academyDao.insertAcademy(academy,collegeId);
    }

    @Override
    public void updateAcademy(Academy academy, Integer collegeId) {
        academyDao.updateAcademy(academy,collegeId);
    }
}
