package com.qmkl.controller;

import com.alibaba.fastjson.JSON;
import com.qmkl.entity.*;
import com.qmkl.service.CommentService;
import com.qmkl.service.PostService;
import com.qmkl.service.PrivateCommentService;
import com.qmkl.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.qmkl.Util.IOUtil.GetInputStreamString;

@RestController
@RequestMapping(value = "/pri/comment", method = RequestMethod.POST)
public class PrivateCommentController {

    @Resource
    PrivateCommentService privateCommentService;

    @Resource
    PostService postService;

    @Resource
    UserService userService;


    @ExceptionHandler(Exception.class)
    public ResponseData handleException(Exception e){
        e.printStackTrace();
        ResponseData responseData = new ResponseData();
        responseData.setStatusError();
        return responseData;
    }

    @Transactional
    @RequestMapping("/add")
    public ResponseData add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        String date = GetInputStreamString(request);
        Map<String,String> map = JSON.parseObject(date,Map.class);
        String token = map.get("token");
        Integer userId = userService.getUserIdByToken(token);
        Integer postId = Integer.valueOf( map.get("postId"));
        if(map.get("postId")==null){
            responseData.setStatusOther("postId不能为空");
            return responseData;
        }
        if(map.get("userId")==null){
            responseData.setStatusOther("userId不能为空");
            return responseData;
        }
        if(userId == null){
            responseData.setStatusNoLogin();
            return responseData;
        }
        Integer userId2 = Integer.valueOf(map.get("userId"));
        PrivateComment privateComment = new PrivateComment(userId, userId2,postId,map.get("content"),new Date());
        System.out.println(privateComment);
        privateCommentService.addComment(privateComment);
        postService.updatePostCommentNum(postId);
        return responseData;
    }


    @Transactional
    @RequestMapping("/get")
    public ResponseData get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        String date = GetInputStreamString(request);
        Map<String,String> map = JSON.parseObject(date,Map.class);
        String token = map.get("token");
        Integer userId = userService.getUserIdByToken(token);
        Integer postId = Integer.valueOf( map.get("postId"));
        if(map.get("postId")==null){
            responseData.setStatusOther("postId不能为空");
            return responseData;
        }
        responseData.setData(privateCommentService.getList(userId, postId));
        return responseData;
    }
}
