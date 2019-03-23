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
@RequestMapping(value = "/comment", method = RequestMethod.POST)
public class CommentController {

    @Resource
    CommentService commentService;

    @Resource
    PostService postService;

    @Resource
    UserService userService;

    @Resource
    PrivateCommentService privateCommentService;

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
  //      System.out.println(postId);
        if(userId == null){
            responseData.setStatusNoLogin();
            return responseData;
        }
        Comment comment = new Comment(userId,postId,map.get("content"),new Date());
        commentService.addComment(comment);
        postService.updatePostCommentNum(postId);
        return responseData;
    }


    @RequestMapping("/list")
    public ResponseData list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        String date = GetInputStreamString(request);
        Map<String,String> map = JSON.parseObject(date,Map.class);
        String token = map.get("token");
        Integer userId = userService.getUserIdByToken(token);
        if(userId == null){
            responseData.setStatusNoLogin();
            return responseData;
        }
        if(map.get("postId")==null){
            responseData.setStatusOther("postId不能为空");
            return responseData;
        }
        Integer postId = Integer.valueOf(map.get("postId"));
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
        List<CommentResult> comments = commentService.listCommentWithPage((page-1)*num,num,postId);
        Map<String,Object> map1 = new HashMap<>();
        List<PrivateCommentResult> privateComments = privateCommentService.getList(userId, postId);
        map1.put("comment", comments);
        map1.put("privateComment", privateComments);
        responseData.setData(map1);
        return responseData;
    }



    @RequestMapping("/user/list")
    public ResponseData userList(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        List<CommentResult> comments = commentService.listCommentWithPageByUserId((page-1)*num,num,userId);
        List<HashMap<String,Object>> result = new ArrayList<>();
        for(CommentResult commentResult: comments){
            PostResult ps = postService.getById(commentResult.getPostId());
            HashMap<String,Object> mp = new HashMap<>();
            mp.put("comment", commentResult);
            mp.put("post", ps);
            result.add(mp);
        }
        responseData.setData(result);
        return responseData;
    }

}
