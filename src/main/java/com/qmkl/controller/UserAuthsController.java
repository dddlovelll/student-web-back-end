package com.qmkl.controller;


import com.alibaba.fastjson.JSON;
import com.qmkl.Util.MyFileUtil;
import com.qmkl.dao.UserAuthsDao;
import com.qmkl.entity.ResponseData;
import com.qmkl.entity.User;
import com.qmkl.entity.UserAuths;
import com.qmkl.service.AcademyService;
import com.qmkl.service.CollegeService;
import com.qmkl.service.UserAuthsService;
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
import java.util.UUID;

import static com.qmkl.Util.IOUtil.GetInputStreamString;
import static com.qmkl.Util.TokenUtil.createToken;


@RestController
@RequestMapping(value = "/userauth", method = RequestMethod.POST)
public class UserAuthsController {

    @Resource
    private UserService userService;

    @Resource
    private UserAuthsService userAuthsService;

    @Resource
    private UserAuthsDao userAuthsDao;

    @Resource
    private CollegeService collegeService;

    @Resource
    private AcademyService academyService;



    @RequestMapping("/login")
    public ResponseData login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String date = GetInputStreamString(request);
        Map<String,String> map = JSON.parseObject(date,Map.class);
        ResponseData responseData = new ResponseData();
        String platformId = map.get("platformId");
        String platform = map.get("platform");
        if(platformId == null || platform == null){
            responseData.setStatusOther("platformId不能为空");
            return responseData;
        }
        if(!(platform.equals("qq") || platform.equals("wx")))
        {
            responseData.setStatusOther("平台代码错误");
            return responseData;
        }
        UserAuths userAuths = userAuthsDao.getUserAuthsByPlatFormIdAndPlatForm(platformId,platform);
        if(userAuths == null){
            userAuths = new UserAuths(platform,platformId,new Date(),new Date());
            userAuthsDao.insertUserAuths(userAuths);
            responseData.setStatusOther("请完善资料");
            return responseData;
        }
        Integer userId = userAuths.getUserId();
        if(userId == null){
            responseData.setStatusOther("请完善资料");
            return responseData;
        }
        User user = userService.selectUserById(userId);
        if(user == null){
            responseData.setStatusOther("请完善资料");
            return responseData;
        }
        String token = createToken(user.getUsername());//创建新token
        userService.updateTokenById(userId,token);
        responseData.setData(token);
        return responseData;
    }



    @Transactional
    @RequestMapping("/update/info")
    public ResponseData updateInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String date = GetInputStreamString(request);
        Map<String,String> map = JSON.parseObject(date,Map.class);
        ResponseData responseData = new ResponseData();
        String platform = map.get("platform");
        String platformId = map.get("platformId");
        UserAuths userAuths = userAuthsDao.getUserAuthsByPlatFormIdAndPlatForm(platformId,platform);
        if(userAuths == null){
            responseData.setStatusOther("没有该账号");
            return responseData;
        }
        if(userAuths.getUserId() != null){
            responseData.setStatusOther("该账号已经注册");
            return responseData;
        }
        String nickname = map.get("nickname");
        if(userService.selectUserByNickname(nickname) != null){
            responseData.setStatusOther("昵称已存在");
            return responseData;
        }
       try{
           String username = UUID.randomUUID().toString();
           String gender = map.get("gender");
           String enterYear = map.get("enterYear");
           String avatar = map.get("avatar");
           String college = map.get("college");
           String academy = map.get("academy");
           String md5 = MyFileUtil.downloadPicture(avatar);
           String sqlAvatar = "upload/avatar/"+md5;
           User user = null;
           if(college != null && academy!= null){
               Integer collegeId = collegeService.selectIdByCollegeName(college);
               Integer academyId = academyService.getIdByAcademyNameAndCollegeId(academy,collegeId);
               user = new User(username,null,nickname,gender,enterYear,sqlAvatar,0,new Date(),new Date(),null,null,null);
               userService.insertUser(user,collegeId,academyId);
               User newUser = userService.selectUserByUsername(username);
               userAuthsDao.updateUserAuths(newUser.getId(),platformId,platform);
           }else {
               responseData.setStatusOther("学校和学院不能为空");
               return responseData;
           }
           String token = createToken(username);//创建新token
           userService.updateTokenByUsername(token,username);
           responseData.setData(token);
       }catch (Exception e){
            responseData.setStatusError();
            return responseData;
       }
        return responseData;
    }




}
