package com.qmkl.dao;
import com.qmkl.entity.College;

import java.util.List;

public interface CollegeDao {
    String selectCollegeNameById(Integer id);//通过id查找College名字
    Integer selectIdByCollegeName(String collegeName);//通过College名字查找id
    List<String> listAllName(); //列举所有的学校



    //未使用
    College selectCollegeById(Integer id);//通过id查找College
    void insertCollege(College college);//插入College
    void updateCollege(College college);//更新College
}
