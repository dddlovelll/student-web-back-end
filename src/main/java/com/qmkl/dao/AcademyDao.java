package com.qmkl.dao;

import com.qmkl.entity.Academy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AcademyDao {
    String selectAcademyNameById(Integer id);//通过id查找Academy名字
    Integer selectIdByAcademyName(String academyName);//通过Academy名字查找id
    List<String> selectAllAcademyName();//通过查询所有的专业名称
    List<String> selectAllAcademyNameByCollegeName(Integer collegeId);//通过学校查询所有的专业名称

    Integer getIdByAcademyNameAndCollegeId(@Param("academyName") String academyName,@Param("collegeId") Integer collegeId);//通过Academy名字查找id


    //未使用
    Academy selectAcademyById(Integer id);//通过id查找Academy
    void insertAcademy(@Param("academy") Academy academy,@Param("collegeId") Integer collegeId);//插入Academy
    void updateAcademy(@Param("academy")Academy academy,@Param("collegeId") Integer collegeId);//更新Academy
}
