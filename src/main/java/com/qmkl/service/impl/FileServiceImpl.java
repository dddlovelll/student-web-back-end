package com.qmkl.service.impl;

import com.qmkl.dao.FileDao;
import com.qmkl.entity.FileDetail;
import com.qmkl.entity.Files;
import com.qmkl.service.FileService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Resource
    private FileDao fileDao;



    @Cacheable(value = "listFileName",key = "#path +  #collegeId")
    @Override
    public List<Files> listFileName(String path, Integer collegeId) {
      //  System.out.println("查找数据库");
        return fileDao.listFileName(path,collegeId);
    }

    @Cacheable(value = "listFileNamePage",key = "#path +  #collegeId + #start + #end")
    @Override
    public List<Files> listFileNamePage(String path, Integer collegeId, Integer start, Integer end) {
        return fileDao.listFileNamePage(path,collegeId,start,end);
    }

    @Override
    public FileDetail getFile(String path, Integer collegeId) {
        return fileDao.getFile(path,collegeId);
    }

    @Override
    public FileDetail getFileByMD5(String md5) {
        return fileDao.getFileByMD5(md5);
    }

    @Override
    public FileDetail getFileBySpath(String spath) {
        return fileDao.getFileBySpath(spath);
    }

    @Override
    public FileDetail getFileByIdAndMd5(Integer id, String md5) {
        return fileDao.getFileByIdAndMd5(id,md5);
    }

    @Override
    public List<FileDetail> getFileBySpathAndMd5(String spath, String md5) {
        return fileDao.getFileBySpathAndMd5(spath,md5);
    }

    @Override
    public List<FileDetail> getListFileByMD5(String md5) {
      //  System.out.println(fileDao.getListFileByMD5(md5));
        return fileDao.getListFileByMD5(md5);
    }

    @Override
    public void addLikeNum(Integer id) {
        fileDao.addLikeNum(id);
    }

    @Override
    public void descLikeNum(Integer id) {
        fileDao.descLikeNum(id);
    }

    @Override
    public void addDiskLikeNum(Integer id) {
        fileDao.addDiskLikeNum(id);
    }

    @Override
    public void descDisLikeNum(Integer id) {
        fileDao.descDisLikeNum(id);
    }
}
