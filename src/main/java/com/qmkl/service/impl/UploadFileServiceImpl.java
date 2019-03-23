package com.qmkl.service.impl;

import com.qmkl.Util.OssUtil;
import com.qmkl.dao.FileDao;
import com.qmkl.dao.UploadFileDao;
import com.qmkl.entity.Files;
import com.qmkl.entity.UploadFile;
import com.qmkl.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private UploadFileDao uploadFileDao;
    @Resource
    private FileDao fileDao;

    @Override
    public UploadFile getUploadFileInfo(String md5) {
        return uploadFileDao.getFileByMd5(md5);
    }

    @Transactional
    @Override
    public void uploadToOss(MultipartFile uploadFile, Files files, Integer collegeId) throws IOException {
        fileDao.saveFile(files,collegeId);
        OssUtil.upload(uploadFile,files.getMd5());
    }
}
