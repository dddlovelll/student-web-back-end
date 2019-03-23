package com.qmkl.controller;

import com.qmkl.entity.ResponseData;
import com.qmkl.service.AdvertService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RestController
@RequestMapping(value = "/ad",method = RequestMethod.POST)
public class AdvertController {

    @Resource
    private AdvertService advertService;

    @RequestMapping("/detail/{remark}")
    public ResponseData detail(@PathVariable("remark")String remark, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        try {
            responseData.setData(advertService.getAdvertByRemark(remark));
        }catch (Exception e){
            responseData.setStatusOther("获取广告错误");
            e.printStackTrace();
        }
        return responseData;
    }

    @RequestMapping("list/detail/{remark}")
    public ResponseData listdetail(@PathVariable("remark")String remark, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        try {
            responseData.setData(advertService.getAdvertByRemarkList(remark));
        }catch (Exception e){
            responseData.setStatusOther("获取广告错误");
            e.printStackTrace();
        }
        return responseData;
    }
}
