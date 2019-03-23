package com.qmkl.controller;

import com.alibaba.fastjson.JSON;
import com.qmkl.entity.Like;
import com.qmkl.entity.ResponseData;
import com.qmkl.service.DisLikeService;
import com.qmkl.service.FileService;
import com.qmkl.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import static com.qmkl.Util.IOUtil.GetInputStreamString;

@RestController
@RequestMapping(value = "/dislike", method = RequestMethod.POST)
public class DisLikeController {

    @Resource
    DisLikeService disLikeService;

    @Resource
    private UserService userService;

    @Resource
    FileService fileService;


    @RequestMapping("/is/dislike")
    public ResponseData disIsLike(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        String date = GetInputStreamString(request);
        Map<String,String> map = JSON.parseObject(date,Map.class);
        try{
            String token = map.get("token");
            if(token==null){
                responseData.setStatusNoLogin();
            }else{
                Integer userId = userService.getUserIdByToken(token);
                if(userId==null){
                    responseData.setStatusNoLogin();
                }else {
                    Integer fileId = Integer.parseInt(map.get("fileId"));
                    if(fileId!=null){
                        Like like = disLikeService.getDisLike(userId,fileId);
                        if(like==null){
                            responseData.setData(false);
                        }else {
                            responseData.setData(like.isStatus());
                        }
                    }else {
                        responseData.setStatusOther("获取踩次数失败");
                    }
                }

            }

        }catch (Exception e){
            responseData.setStatusError();
        }
        return responseData;
    }




    @Transactional
    @RequestMapping("/addordesc")
    public ResponseData addOrDesc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        String date = GetInputStreamString(request);
        Map<String,String> map = JSON.parseObject(date,Map.class);
        try{
            String token = map.get("token");
            if(token==null){
                responseData.setStatusNoLogin();
            }else{
                Integer userId = userService.getUserIdByToken(token);
                if(userId==null){
                    responseData.setStatusNoLogin();
                }else {
                    Integer fileId = Integer.parseInt(map.get("fileId"));
                    if(fileId!=null){
                        Like like = disLikeService.getDisLike(userId,fileId);
                        if(like==null){
                            disLikeService.addDisLike(userId,new Date(),fileId,true);
                            fileService.addDiskLikeNum(fileId);
                        }else {
                            if(like.isStatus()){
                                disLikeService.updateDisLikeStatus(userId,fileId);
                                fileService.descDisLikeNum(fileId);
                            }else {
                                disLikeService.updateDisLikeStatus(userId,fileId);
                                fileService.addDiskLikeNum(fileId);
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            responseData.setStatusOther("踩失败");
        }
        return responseData;
    }



}
