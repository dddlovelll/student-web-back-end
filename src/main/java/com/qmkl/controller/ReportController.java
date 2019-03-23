package com.qmkl.controller;

import com.alibaba.fastjson.JSON;
import com.qmkl.entity.Like;
import com.qmkl.entity.PostLike;
import com.qmkl.entity.Report;
import com.qmkl.entity.ResponseData;
import com.qmkl.service.*;
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
@RequestMapping(value = "/report", method = RequestMethod.POST)
public class ReportController {

    @Resource
    ReportService reportService;

    @Resource
    PostService postService;

    @Resource
    UserService userService;

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
        Report r = reportService.get(userId,postId);
        if(r!=null && r.isState()){
            responseData.setStatusOther("请不要重复举报");
            return responseData;
        }
        String content = map.get("content");
        if(content == null){
            content = "";
        }
        Report report = new Report(userId,postId,content,new Date(),true);
        reportService.addReport(report);
        postService.updatePostAddReportNum(postId);
        return responseData;
    }


    @RequestMapping("/isreport")
    public ResponseData isreport(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        Report r = reportService.get(userId,postId);
        if(r!=null){
            responseData.setData(r.isState());
        }else {
            responseData.setData(false);
        }

        return responseData;
    }
}
