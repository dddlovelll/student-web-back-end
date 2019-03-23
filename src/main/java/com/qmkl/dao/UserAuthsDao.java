package com.qmkl.dao;


import com.qmkl.entity.UserAuths;
import org.apache.ibatis.annotations.Param;

public interface UserAuthsDao {
    Integer getUserIdByPlatFormIdAndPlatForm(@Param("platformId") String platformId,@Param("platform") String platform);
    UserAuths getUserAuthsByPlatFormIdAndPlatForm(@Param("platformId") String platformId, @Param("platform") String platform);
    void insertUserAuths(@Param("userAuths")UserAuths userAuths);
    void updateUserAuths(@Param("userId")Integer userId,@Param("platformId") String platformId, @Param("platform") String platform);
}
