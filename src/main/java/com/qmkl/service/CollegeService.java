package com.qmkl.service;
import com.qmkl.entity.College;

import java.util.List;

public interface CollegeService {
    String selectCollegeNameById(Integer id);//通过id查找College名字
    Integer selectIdByCollegeName(String collegeName);//通过College名字查找id
    List<String> listAllName(); //列举所有的学校


    College selectCollegeById(Integer id);//通过id查找College
    void insertCollege(College college);//插入College
    void updateCollege(College college);//更新College
}
