package com.qmkl.service;

import com.qmkl.entity.Academy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AcademyService {
    String selectAcademyNameById(Integer id);//通过id查找Academy名字
    Integer selectIdByAcademyName(String academyName); //通过Academy名字查找id
    List<String> selectAllAcademyName();//通过查询所有的专业名称
    List<String> selectAllAcademyNameByCollegeName(Integer collegeId);//通过学校查询所有的专业名称
    Integer getIdByAcademyNameAndCollegeId(@Param("academyName") String academyName,@Param("collegeId") Integer collegeId);//通过Academy名字查找id

    Academy selectAcademyById(Integer id);//通过id查找Academy
    void insertAcademy( Academy academy,Integer collegeId);//插入Academy
    void updateAcademy(Academy academy, Integer collegeId);//更新Academy
}
