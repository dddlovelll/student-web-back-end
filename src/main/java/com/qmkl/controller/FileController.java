package com.qmkl.controller;

import com.alibaba.fastjson.JSON;
import com.qmkl.MyCompares.MyCompar;
import com.qmkl.entity.FileDetail;
import com.qmkl.entity.Files;
import com.qmkl.entity.ResponseData;
import com.qmkl.service.CollegeService;
import com.qmkl.service.FileService;
import com.qmkl.service.UserService;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static com.qmkl.Util.ChangeSize.fileSizeConver;
import static com.qmkl.Util.IOUtil.GetInputStreamString;
import static com.qmkl.Util.IOUtil.OutResponseData;
import static com.qmkl.Util.OssUtil.CreateUrl;

@RestController
@RequestMapping(value = "/file", method = RequestMethod.POST)
public class FileController {

    private HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

    @Resource
    FileService fileService;

    @Resource
    private CollegeService collegeService;

    @Resource
    private UserService userService;

    @RequestMapping("/list")
    public ResponseData list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        try {
            String date = GetInputStreamString(request);
            Map<String, String> map = JSON.parseObject(date, Map.class);
            String token = map.get("token");
            String username = userService.getUsernameByToken(token);
            if (username == null) {
                responseData.setStatusNoLogin();
            } else {
                String path = map.get("path");
                Integer collegeId = collegeService.selectIdByCollegeName(map.get("collegeName"));
                int length = path.length();
                List<Files> fileNameList = fileService.listFileName(path, collegeId);
                Map<String, String> resultMap = new TreeMap<String, String>(new MyCompar());
                String page = map.get("page");
                String pageNum = map.get("pageNum");
                for (Files files : fileNameList) {
                    String str = files.getSpath().substring(length).split("/")[0];
                    if (!resultMap.containsKey(str) && str.length() > 0) {
                        resultMap.put(str, fileSizeConver(files.getSize()));
                    }
                }
                if (page == null || page.equals("") || pageNum == null || pageNum.equals("")) {
                    responseData.setData(resultMap);
                } else {
                    Integer num = Integer.valueOf(pageNum);
                    Integer start = (Integer.valueOf(page) - 1) * num;
                    Integer end = start + num;
                    int i = 0;
                    Map<String, String> resultMap2 = new TreeMap<String, String>(new MyCompar());
                    Iterator titer = resultMap.entrySet().iterator();
                    while (titer.hasNext()) {
                        Map.Entry ent = (Map.Entry) titer.next();
                        String keyt = ent.getKey().toString();
                        String valuet = ent.getValue().toString();
                        if(i>= start && i < end){
                            resultMap2.put(keyt, valuet);
                        }
                        i++;
                    }
                    responseData.setData(resultMap2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setStatusOther("获取文件信息失败");
        }
        return responseData;
    }


    @RequestMapping("/list/detail")
    public ResponseData listDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        try {
            String date = GetInputStreamString(request);
            Map<String, String> map = JSON.parseObject(date, Map.class);
            String token = map.get("token");
            String username = userService.getUsernameByToken(token);
            if (username == null) {
                responseData.setStatusNoLogin();
            } else {
                String path = map.get("path");
                Integer collegeId = collegeService.selectIdByCollegeName(map.get("collegeName"));
                FileDetail fileDetail = fileService.getFile(path, collegeId);
                System.out.println(fileDetail);
                fileDetail.setSize(fileSizeConver(Integer.parseInt(fileDetail.getSize())));
                responseData.setData(fileDetail);
            }
        } catch (Exception e) {
            responseData.setStatusError();
        }
        return responseData;
    }


    @RequestMapping("/download/url")
    public ResponseData downloadUrl(HttpServletRequest HttpRequest, HttpServletResponse response) throws ParseException, IOException {
        ResponseData responseData = new ResponseData();
        try {
            String date = GetInputStreamString(HttpRequest);
            Map<String, String> map = JSON.parseObject(date, Map.class);
            String token = map.get("token");
            String username = userService.getUsernameByToken(token);
            if (username == null) {
                responseData.setStatusNoLogin();
            } else {
                String path = map.get("path");
                Integer collegeId = collegeService.selectIdByCollegeName(map.get("collegeName"));
                FileDetail fileDetail = fileService.getFile(path, collegeId);
                if (fileDetail != null) {
                    String url = CreateUrl(fileDetail.getMd5());
                    Map<String, String> resultMap = new HashMap<>();
                    String[] str = fileDetail.getName().split("/");
                    resultMap.put("fileName", str[str.length - 1]);
                    resultMap.put("url", url);
                    responseData.setData(resultMap);
                } else {
                    responseData.setStatusOther("找不到文件");
                }
            }
        } catch (Exception e) {
            responseData.setStatusError();
        }
        return responseData;
    }


}
