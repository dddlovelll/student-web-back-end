package com.qmkl.controller;

import com.alibaba.fastjson.JSON;
import com.qmkl.entity.Comment;
import com.qmkl.entity.PostLike;
import com.qmkl.entity.PostLikeResult;
import com.qmkl.entity.ResponseData;
import com.qmkl.service.PostLikeService;
import com.qmkl.service.PostService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.qmkl.Util.IOUtil.GetInputStreamString;

@RestController
@RequestMapping(value = "/post/like", method = RequestMethod.POST)
public class PostLikeController {

    @Resource
    PostLikeService postLikeService;

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


    @RequestMapping("/me")
    public ResponseData me(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        List<Integer> postList = postService.getAllPostIdByUserId(userId);
     //   System.out.println(postList);
        List<PostLikeResult> likeResults = postLikeService.listLikeMe((page-1)*num,num,postList);
        responseData.setData(likeResults);
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
        if(userId == null){
            responseData.setStatusNoLogin();
            return responseData;
        }
        PostLike postLike = postLikeService.get(userId,postId);
        if(postLike == null){
            postLikeService.addPostLike(new PostLike(userId,postId,new Date(),true));
            postService.updatePostAddLikeNum(postId);
        }else {
            if(postLike.isState()){
                postService.updatePostDescLikeNum(postId);
            }else {
                postService.updatePostAddLikeNum(postId);
            }
            postLikeService.updatePostLike(userId,postId);
        }
        return responseData;
    }


    @RequestMapping("/islike")
    public ResponseData islike(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        String date = GetInputStreamString(request);
        Map<String,String> map = JSON.parseObject(date,Map.class);
        String token = map.get("token");
        Integer userId = userService.getUserIdByToken(token);
        Integer postId = Integer.valueOf( map.get("postId"));
        if(userId == null){
            responseData.setStatusNoLogin();
            return responseData;
        }
        PostLike postLike = postLikeService.get(userId,postId);
        if(postLike == null){
            responseData.setData(false);
        }else {
            responseData.setData(postLike.isState());
        }
        return responseData;
    }


}
