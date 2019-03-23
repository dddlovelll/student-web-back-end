package com.qmkl.controller;

import com.alibaba.fastjson.JSON;
import com.qmkl.entity.Like;
import com.qmkl.entity.ResponseData;
import com.qmkl.service.FileService;
import com.qmkl.service.LikeService;
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
import static com.qmkl.Util.IOUtil.OutResponseData;

@RestController
@RequestMapping(value = "/like", method = RequestMethod.POST)
public class LikeController {

    @Resource
    LikeService likeService;

    @Resource
    private UserService userService;

    @Resource
    FileService fileService;


    /* fileId token
    * */
    @RequestMapping("/is/like")
    public ResponseData isLike(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                        Like like = likeService.getLike(userId,fileId);
                        if(like==null){
                            responseData.setData(false);
                        }else {
                            responseData.setData(like.isStatus());
                        }
                    }else {
                        responseData.setStatusOther("获取点赞次数失败");
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
                        Like like = likeService.getLike(userId,fileId);
                        if(like==null){
                            likeService.addLike(userId,new Date(),fileId,true);
                            fileService.addLikeNum(fileId);
                        }else {
                            if(like.isStatus()){
                                likeService.updateLikeStatus(userId,fileId);
                                fileService.descLikeNum(fileId);
                            }else {
                                likeService.updateLikeStatus(userId,fileId);
                                fileService.addLikeNum(fileId);
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            responseData.setStatusOther("点赞失败");
        }
        return responseData;
    }

}
