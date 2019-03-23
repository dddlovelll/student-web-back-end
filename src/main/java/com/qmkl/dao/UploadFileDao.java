package com.qmkl.dao;

import com.qmkl.entity.UploadFile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadFileDao {

    UploadFile getFileByMd5(String md5);

}
