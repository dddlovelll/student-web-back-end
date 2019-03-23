package com.qmkl.service;

import com.qmkl.entity.Files;
import com.qmkl.entity.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface UploadFileService {
    UploadFile getUploadFileInfo(String md5);
    void uploadToOss(MultipartFile uploadFile,Files files, Integer collegeId) throws IOException;
}
