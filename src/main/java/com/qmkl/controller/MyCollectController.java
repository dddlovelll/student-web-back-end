package com.qmkl.controller;

import com.alibaba.fastjson.JSON;
import com.qmkl.entity.MyCollect;
import com.qmkl.entity.MyCollectResult;
import com.qmkl.entity.ResponseData;
import com.qmkl.service.CollegeService;
import com.qmkl.service.MyCollectService;
import com.qmkl.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.qmkl.Util.IOUtil.GetInputStreamString;
import static com.qmkl.Util.IOUtil.OutResponseData;

@RestController
@RequestMapping(value = "/collect",method = RequestMethod.POST)
public class MyCollectController {

    @Resource
    private MyCollectService myCollectService;

    @Resource
    private UserService userService;


    @ExceptionHandler(Exception.class)
    public ResponseData handleException(Exception e){
        e.printStackTrace();
        ResponseData responseData = new ResponseData();
        responseData.setStatusError();
        return responseData;
    }


    @RequestMapping( "/aoc")
    public ResponseData addOrCancel(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        String date = GetInputStreamString(request);
        Map<String,String> map = JSON.parseObject(date,Map.class);
        String token = map.get("token");
        Integer userId = userService.getUserIdByToken(token);
        if(map.get("postId") == null){
            responseData.setStatusOther("postId不能为空");
            return responseData;
        }
        Integer postId = Integer.valueOf( map.get("postId"));
        if(userId == null){
            responseData.setStatusNoLogin();
            return responseData;
        }

        MyCollect sqlCollect = myCollectService.get(userId,postId);
        if(sqlCollect == null){
            MyCollect myCollect = new MyCollect(userId,postId,new Date(),true);
            myCollectService.add(myCollect);
            return responseData;
        }
        if(sqlCollect.getState()){
            myCollectService.update(userId,postId,false);
        }else {
            myCollectService.update(userId,postId,true);
        }
        return responseData;
    }



    @RequestMapping( "/list")
    public ResponseData list(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        String date = GetInputStreamString(request);
        Map<String,String> map = JSON.parseObject(date,Map.class);
        String token = map.get("token");
        Integer userId = userService.getUserIdByToken(token);
        if(userId == null){
            responseData.setStatusNoLogin();
            return responseData;
        }
        Integer page;
        Integer num;
        if(map.get("page") != null){
            page =Integer.valueOf(map.get("page"));
        }else {
            page = 1;
        }
        if(map.get("num") != null){
            num =Integer.valueOf(map.get("num"));
        }else {
            num = 10;
        }
        List<MyCollectResult> list = myCollectService.listMyCollectWithPage((page-1)*num,num,userId);
        responseData.setData(list);
        return responseData;
    }



    @RequestMapping("/iscollect")
    public ResponseData isCollect( HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        String date = GetInputStreamString(request);
        Map<String,String> map = JSON.parseObject(date,Map.class);
        String token = map.get("token");
        Integer userId = userService.getUserIdByToken(token);
        if(userId == null){
            responseData.setStatusNoLogin();
            return responseData;
        }
        if(map.get("postId") == null){
            responseData.setStatusOther("postId不能为空");
            return responseData;
        }
        Integer postId = Integer.parseInt(map.get("postId"));
        MyCollect myCollect = myCollectService.get(userId,postId);
        if(myCollect == null){
            responseData.setData(false);
        }else {
            System.out.println(myCollect);
            responseData.setData(myCollect.getState());
        }

        return responseData;
    }

}
