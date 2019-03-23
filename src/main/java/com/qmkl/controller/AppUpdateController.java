package com.qmkl.controller;


import com.alibaba.fastjson.JSON;
import com.qmkl.entity.Advert;
import com.qmkl.entity.UpdateInfo;
import com.qmkl.service.AdvertService;
import com.qmkl.service.UpdateInfoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

import static com.qmkl.Util.IOUtil.GetInputStreamString;

@RestController
@RequestMapping(value = "/app")
public class AppUpdateController {


    @Resource
    private UpdateInfoService updateInfoService;

    @RequestMapping("/update/{versionCode}")
    public UpdateInfo update(@PathVariable Integer versionCode, HttpServletRequest request){
      //  System.out.println("444");
        UpdateInfo updateInfo = updateInfoService.getLastUpdateInfo();
        if (versionCode < updateInfo.getVersionCode()){
            updateInfo.setHasUpdate(true);
        }else {
            updateInfo.setHasUpdate(false);
        }
     //   System.out.println(updateInfo);
        return updateInfo;
    }


}
