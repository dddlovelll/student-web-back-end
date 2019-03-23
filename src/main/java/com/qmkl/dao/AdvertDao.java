package com.qmkl.dao;

import com.qmkl.entity.Advert;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AdvertDao {
    Advert getAdvertByRemark(String remark);  // 通过remark获取advert
    List<Advert> getAdvertByRemarkList(String remark);  // 通过remark获取advert
    void  saveAdDate(@Param("ip") String ip, @Param("createdAt") Date createdAt, @Param("type") String type, @Param("remark") String remark);  // 保存访问的用户信息
}
