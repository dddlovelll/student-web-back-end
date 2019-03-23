package com.qmkl.service;

import com.qmkl.entity.FileDetail;
import com.qmkl.entity.Files;
import org.apache.ibatis.annotations.Param;

import java.io.File;
import java.util.List;

public interface FileService {
    List<Files> listFileName(@Param("path") String path, @Param("collegeId") Integer collegeId);
    List<Files> listFileNamePage(@Param("path") String path, @Param("collegeId") Integer collegeId,
                                 @Param("start") Integer start, @Param("end") Integer end);
    FileDetail getFile(@Param("path") String path, @Param("collegeId") Integer collegeId);
    FileDetail getFileByMD5(String md5);
    FileDetail getFileBySpath(String spath);
    FileDetail getFileByIdAndMd5(@Param("id") Integer id,@Param("md5")String md5);
    List<FileDetail> getFileBySpathAndMd5(@Param("spath") String spath,@Param("md5")String md5);

    List<FileDetail> getListFileByMD5(String md5);
    void addLikeNum(Integer id);  // 增加 like
    void descLikeNum(Integer id);  // 减少 like
    void addDiskLikeNum(Integer id);  // 增加 dislike
    void descDisLikeNum(Integer id); // 减少 dislike
}
