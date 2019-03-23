package com.qmkl.controller;

import com.alibaba.fastjson.JSON;
import com.qmkl.entity.Post;
import com.qmkl.entity.PostResult;
import com.qmkl.entity.ResponseData;
import com.qmkl.service.PostService;
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

@RestController
@RequestMapping(value = "/post", method = RequestMethod.POST)
public class PostController {

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

    @RequestMapping("/get/{postId}")
    public ResponseData get(@PathVariable Integer postId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        String date = GetInputStreamString(request);
        Map<String,String> map = JSON.parseObject(date,Map.class);
        String token = map.get("token");
        Integer userId = userService.getUserIdByToken(token);
        if(userId == null){
            responseData.setStatusNoLogin();
            return responseData;
        }
        responseData.setData(postService.getById(postId));
        return responseData;
    }



    @RequestMapping("/add")
    public ResponseData add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        String date = GetInputStreamString(request);
        Map<String,String> map = JSON.parseObject(date,Map.class);
        String token = map.get("token");
        Integer userId = userService.getUserIdByToken(token);
        if(userId == null){
            responseData.setStatusNoLogin();
            return responseData;
        }
      //  int a = 1/0;
        Post post = new Post(userId,map.get("content"),new Date(),0,0);
        String str = map.get("classify");
        if(str != null){
            try {
                Integer classify = Integer.valueOf(str);
                post.setClassify(classify);
            }catch (Exception e){
                responseData.setMsg("类别为整数");
                return responseData;
            }
        }else {
            post.setClassify(0);
        }
        postService.addPost(post);
        return responseData;
    }


    @RequestMapping("/del")
    public ResponseData delPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        String date = GetInputStreamString(request);
        Map<String,String> map = JSON.parseObject(date,Map.class);
        String token = map.get("token");
        Integer userId = userService.getUserIdByToken(token);
        if(userId == null){
            responseData.setStatusNoLogin();
            return responseData;
        }
        Integer postId = Integer.valueOf(map.get("postId"));
        PostResult ps = postService.getById(postId);
        if(ps == null){
            responseData.setStatusOther("帖子不存在");
            return responseData;
        }
        if(!ps.getUserId().equals(userId)){
            responseData.setStatusOther("该帖子不是你的");
            return responseData;
        }

        postService.delById(postId);
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
        Integer page;
        Integer num;
        Integer classify;
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
        List<PostResult> posts = null;
        if(map.get("classify") != null){
            try {
                classify =Integer.valueOf(map.get("classify"));
                posts = postService.listPostWithPageWithClassify((page-1)*num,num,classify);
            }catch (Exception e){
                e.printStackTrace();
                responseData.setMsg("classify必须为整数");
                return responseData;
            }
        }else {
            posts = postService.listPostWithPage((page-1)*num,num);
        }
        responseData.setData(posts);
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
        List<PostResult> posts = postService.listPostWithPageByUserId((page-1)*num,num,userId);
        responseData.setData(posts);
        return responseData;
    }



}
