package com.qmkl.controller;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.qmkl.entity.ResponseData;
import com.qmkl.entity.User;
import com.qmkl.service.AcademyService;
import com.qmkl.service.CollegeService;
import com.qmkl.service.SmsService;
import com.qmkl.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import static com.qmkl.Util.IOUtil.GetInputStreamString;
import static com.qmkl.Util.IOUtil.OutResponseData;
import static com.qmkl.Util.TokenUtil.VerifyToken;
import static com.qmkl.Util.TokenUtil.createToken;



@RequestMapping(value = "/user",method = RequestMethod.POST)
@RestController
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private SmsService smsService;
    @Resource
    private CollegeService collegeService;
    @Resource
    private AcademyService academyService;


    private File fileDir=new File(File.separator);

   //  private final String SaveFilePath = "C:\\Users\\win10\\Desktop\\qmkl\\upload\\avatar\\";  // 本地测试路径

    private final String SaveFilePath = "//home//upload//avatar//"; //服务器上的路径
    private final String SqlSaveFilePath = "upload/avatar/"; //服务器上的路径

    private final String defaultAvatar = "d8f655caa433baff1cd93f1f19d5aaf0.png";

    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String date = GetInputStreamString(request);
        Map<String,String> map = JSON.parseObject(date,Map.class);
        ArrayList<String> files = new ArrayList<String>();

        File fDir=new File(File.separator); //File.separator表示根目录，比如现在就表示在D盘下。
        System.out.println(fDir.getAbsolutePath());
        File file = new File(fDir.getAbsolutePath()+map.get("path"));
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
//              System.out.println("文     件：" + tempList[i]);
                files.add(tempList[i].toString());
            }
            if (tempList[i].isDirectory()) {
//              System.out.println("文件夹：" + tempList[i]);
            }
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println(JSON.toJSONString(files));
        out.flush();
        out.close();
    }



    @RequestMapping("/data")
    public void data(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String date = GetInputStreamString(request);
        PrintWriter out=response.getWriter();
        out.println(date);
        out.flush();
        out.close();
    }


    //登录
    @RequestMapping("/login")
    public ResponseData login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = null;
        try {
            responseData = new ResponseData();
            String date = GetInputStreamString(request);
            Map<String,Object> map = JSON.parseObject(date,Map.class);
            if(map.containsKey("token")) {
                String oldToken = map.get("token").toString(); // 原来的token
                Map<String,Claim> claims = VerifyToken(oldToken); // 判断是否过期
                String username = claims.get("username").asString();
                if (userService.selectTokenByUsername(username).equals(oldToken)) {
                    String token = createToken(username);//创建新token
                    userService.updateTokenByUsername(token, username);//更新数据库
                    responseData.setMsg("登录成功");
                    responseData.setData(token);
                } else {
                    responseData.setStatusOther("登录失败");
                }
            }else {
                if(map.containsKey("username") && map.containsKey("password")) {
                    String username = map.get("username").toString();
                    String password = map.get("password").toString();
                    Integer id = userService.selectUserByUsernameAndPassword(username,password);
                    if(id!=null) {
                        String token=createToken(username); // 创建新token
                        userService.updateTokenByUsername(token,username); // 更新数据库token
                        userService.updateTimeByUserName(username,new Date()); // 更新数据库的时间
                        responseData.setMsg("登录成功");
                        responseData.setData(token);
                    }else {
                        responseData.setStatusOther("账号密码错误");
                    }
                }
            }
        }catch (Exception e)
        {
            responseData.setStatusError();
        }
        return responseData;
    }


    //退出登录
    @RequestMapping("/out")
    public ResponseData out(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        String date = GetInputStreamString(request);
        try{
            Map<String,String> map = JSON.parseObject(date,Map.class);
            String username = map.get("username");
            if(username!=null) {
                userService.updateTokenByUsername(createToken(username),username);
            }
        }catch (Exception e){
            responseData.setStatusError();
        }
        return responseData;
    }



    // 通过username获取用户信息
    @RequestMapping("/info")
    public ResponseData GetUserInfoById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //System.out.println("/user/info");
        ResponseData responseData = null;
        try {
            responseData = new ResponseData();
            String date = GetInputStreamString(request);
            Map<String,String> map = JSON.parseObject(date,Map.class);
            if(map.containsKey("token")) {
                String token = map.get("token");  //原来的token
                String username = userService.getUsernameByToken(token);
                if(username!=null) {
             //       Map<String,Claim> claims = VerifyToken(token);//判断是否过期
                    User user = userService.selectUserByUsername(username);
//                    //System.out.println(user.getAvatar());
                    if(user.getAvatar()!=null && user.getAvatar().split("/").length>=2){
                        user.setAvatar(user.getAvatar().split("/")[2]);
                    }else {
                        user.setAvatar(defaultAvatar);
                    }
                    responseData.setData(user);
                }else {
                    responseData.setStatusNoLogin();
                }
            }
        }catch (Exception e) {
           responseData.setStatusError();
           e.printStackTrace();
        }
        return responseData;
    }



    //更新用户信息
    @RequestMapping("/update/info")
    public ResponseData updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = null;
        try {
            responseData = new ResponseData();
            String date = GetInputStreamString(request);
            Map<String,Object> map = JSON.parseObject(date,Map.class);
            String token = map.get("token").toString();
            String username = userService.getUsernameByToken(token);
            if(username == null) {
              responseData.setStatusNoLogin();
            }else {
            //    Map<String,Claim> claims = VerifyToken(token); // 判断是否过期
                String userString = map.get("user").toString();
                User user = JSON.parseObject(userString,User.class);
                user.setUsername(username);
                if(userService.selectUserByNickname(user.getNickname())!=null) {
                  responseData.setStatusOther("昵称已存在");
                } else {
                    user.setUpdatedAt(new Date());
                   // System.out.println(user);
                    if(user.getCollege() == null || user .getCollege().equals("")){
                        userService.updateUser(user,null,null);
                    }else {
                        Integer collegeId = collegeService.selectIdByCollegeName(user.getCollege());
                        Integer academyId = academyService.getIdByAcademyNameAndCollegeId(user.getAcademy(),collegeId);
                        userService.updateUser(user,collegeId,academyId);
                    }
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
           responseData.setStatusError();
        }
       return responseData;
    }



    // 更新用户密码
    @RequestMapping("/update/password")
    public ResponseData updateUserPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        try {
            String date = GetInputStreamString(request);
            Map<String,String> map = JSON.parseObject(date,Map.class);
            String token = map.get("token");
            Map<String,Claim> claims = VerifyToken(token);//判断是否过期
            String phone = map.get("phone");
            String vercode = map.get("vercode");
            //String username = claims.get("username").asString();
            if(phone!=null && vercode!=null) {
                if(token.equals(smsService.selectSmsTokenCodeByPhoneAndVercode(phone,vercode))) {
                    String password = map.get("password");
                    userService.updateUserPasswordByUserName(phone,password);
                    smsService.updateSmsTokenCodeByPhoneAndVercode(phone,vercode);
                }else {
                    responseData.setStatusOther("验证码错误");
                }
            }
        }catch (RuntimeException e) {
            responseData.setStatusOther("验证码已过期");
           // e.printStackTrace();
        } catch (Exception e) {
            responseData.setStatusError();
        }
       return responseData;
    }


    // 添加新用户
    @RequestMapping("/vercode")
    public ResponseData vercode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = null;
        try {
            responseData = new ResponseData();
            String date = GetInputStreamString(request);
            Map<String, String> map = JSON.parseObject(date, Map.class);
            String token = map.get("token");
            Map<String, Claim> claims = VerifyToken(token);//判断是否过期
            String phone = map.get("phone");
       //     String password = map.get("password");
            String vercode = map.get("vercode");

            if(phone!=null  && vercode!=null) {
                if (token.equals(smsService.selectSmsTokenCodeByPhoneAndVercode(phone, vercode))) {
                    smsService.updateSmsTokenCodeByPhoneAndVercode(phone, vercode);
                } else {
                    responseData.setStatusOther("验证码错误");
                }
            }
        } catch (RuntimeException e) {
            responseData.setStatusOther("验证码错误");
        } catch (Exception e) {
            responseData.setStatusError();
        }
        return responseData;
    }



    // 添加新用户 s phone ,password,nickname.gender,enteryear,academyName,collegeName,phone
    @RequestMapping("/all/info")
    public ResponseData allInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = null;
        try {
            responseData = new ResponseData();
            String date = GetInputStreamString(request);
            User user = JSON.parseObject(date, User.class);
            if(userService.selectUserByNickname(user.getNickname())!=null){
                responseData.setStatusOther("昵称已存在");
            }else if(userService.selectUserByUsername(user.getUsername())!=null){
                responseData.setStatusOther("手机号已注册");
            }
            else if(userService.selectUserByPhone(user.getUsername())!=null){
                responseData.setStatusOther("手机号已注册");
            }else {
                Integer collegeId = collegeService.selectIdByCollegeName(user.getCollege());
                Integer academyId = academyService.getIdByAcademyNameAndCollegeId(user.getAcademy(),collegeId);
                user.setIsAdmin(0);
                user.setCreatedAt(new Date());
                user.setUpdatedAt(new Date());
                user.setAvatar(SqlSaveFilePath+defaultAvatar);
                userService.insertUser(user,collegeId,academyId);
                String token = createToken(user.getUsername());
                userService.updateTokenByUsername(token,user.getUsername());
                responseData.setData(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setStatusOther("完善信息失败");
        }
        return responseData;
    }

    @RequestMapping(value = "/update/avatar")
    public ResponseData updateAvatar(@RequestParam("avatar")MultipartFile file,@RequestParam("token") String token, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        try{
            String username = userService.getUsernameByToken(token);
            if(username!=null) {
                String originalFilename = file.getOriginalFilename();
                String fileName = DigestUtils.md5Hex(file.getInputStream());
                if(originalFilename.contains(".")) {
                    fileName=fileName+"."+originalFilename.split("\\.")[1];
                }
               // System.out.println(fileName);
                File path = new File(SaveFilePath+fileName);
                if(!path.exists()){
                    path.createNewFile();
                    InputStream in = file.getInputStream();
                    FileOutputStream out = new FileOutputStream(path);
                    byte[] media = new byte[1024];
                    int length = in.read(media, 0, 1024);
                    while(length  != -1) {
                        out.write(media, 0, length);
                        length = in.read(media, 0, 1024);
                    }
                    in.close();
                    out.close();
                }
                userService.updateAvatar(username,SqlSaveFilePath+fileName);
                responseData.setData(fileName);
            }else {
                responseData.setStatusNoLogin();
            }
        }catch (Exception e)
        {
            responseData.setStatusError();
        }
        return responseData;
    }


    @RequestMapping(value = "/download/avatar/{path}",method = RequestMethod.GET)
    public void downloadAvatar(@PathVariable String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
       File file = new File(SaveFilePath+path);
       FileInputStream inputStream = null;
       if(file.exists()) {
           inputStream = new FileInputStream(SaveFilePath+path);
       }else {
           inputStream = new FileInputStream(SaveFilePath+defaultAvatar);
       }
        int i = inputStream.available();
        //byte数组用于存放图片字节数据
        byte[] buff = new byte[i];
        inputStream.read(buff);
        //记得关闭输入流
        inputStream.close();
        //设置发送到客户端的响应内容类型
        response.setContentType("image/*");
        //response.setContentType("text/html; charset=utf-8");
        OutputStream out = response.getOutputStream();
        out.write(buff);
        //关闭响应输出流
        out.close();
    }


    @RequestMapping(value = "/download/avatar/id/{id}",method = RequestMethod.GET)
    public void downloadAvatar(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userService.selectUserById(id);
        String path;
        if(user==null ){
            path = defaultAvatar;
        }else if(user.getAvatar()!=null){
            path = user.getAvatar().split("/")[2];
        }else {
            path = defaultAvatar;
        }
        File file = new File(SaveFilePath+path);
        FileInputStream inputStream = null;
        if(file.exists()) {
            inputStream = new FileInputStream(SaveFilePath+path);
        }else {
            inputStream = new FileInputStream(SaveFilePath+defaultAvatar);
        }
        int i = inputStream.available();
        //byte数组用于存放图片字节数据
        byte[] buff = new byte[i];
        inputStream.read(buff);
        //记得关闭输入流
        inputStream.close();
        //设置发送到客户端的响应内容类型
        response.setContentType("image/*");
        //response.setContentType("text/html; charset=utf-8");
        OutputStream out = response.getOutputStream();
        out.write(buff);
        //关闭响应输出流
        out.close();
    }




    // 更新用户密码
    @RequestMapping("/update/password2")
    public ResponseData updateUserPassword2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        try {
            String date = GetInputStreamString(request);
            Map<String,String> map = JSON.parseObject(date,Map.class);
            String token = map.get("token");
            String username = userService.getUsernameByToken(token);
            String  oldpassword = map.get("oldpassword");
            String  newpassword = map.get("newpassword");
            Integer userId = userService.selectUserByUsernameAndPassword(username,oldpassword);
            if(userId == null){
                responseData.setStatusOther("原密码错误");
            }else {
                userService.updateUserPasswordByUserName(username,newpassword);
            }
        }catch (Exception e) {
            responseData.setStatusError();
        }
        return responseData;
    }





}
