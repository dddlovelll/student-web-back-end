package com.qmkl.controller;

import com.qmkl.entity.UploadFile;
import com.qmkl.service.CollegeService;
import com.qmkl.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/admin")
public class AdminFileController {

    @Autowired
    private UploadFileService uploadFileService;

    @Resource
    private CollegeService collegeService;

    @RequestMapping("/get/{md5}")
    public Object get(@PathVariable("md5") String md5){
        if(md5!=null && !md5.equals("")){
            UploadFile uploadFile = uploadFileService.getUploadFileInfo(md5);
            if(uploadFile!=null && uploadFile.isAnonymous()){
                uploadFile.setUserId(69);
            }
            return uploadFile;
        }
        return null;
    }

}
