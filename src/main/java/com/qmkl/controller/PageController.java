package com.qmkl.controller;

import com.qmkl.Util.CookieUtil;
import com.qmkl.Util.NetworkUtil;
import com.qmkl.Util.TokenUtil;
import com.qmkl.entity.FileDetail;
import com.qmkl.entity.Files;
import com.qmkl.entity.User;
import com.qmkl.service.CollegeService;
import com.qmkl.service.FileService;
import com.qmkl.service.UploadFileService;
import com.qmkl.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.qmkl.Util.ShaUtil.encode;

@Controller
public class PageController {

    private final static String prefix = "http://120.77.32.233/qmkl1.0.0/user/download/avatar/";


    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private FileService fileService;

    @Resource
    private CollegeService collegeService;

    @Resource
    private UserService userService;

    @RequestMapping({"/login", "/index"})
    public String index(HttpServletRequest request) {
        String token = CookieUtil.get(request);
        User user = userService.getUserByToken(token);
        if(user!=null && user.getIsAdmin()==1){
            return "upload";
        }
        return  "login";
    }

/*    @RequestMapping("/upload")
    public String upload() {
        return  "upload";
    }*/

    @RequestMapping("/admin_login")
    public ModelAndView adminLogin(HttpServletRequest request, HttpServletResponse response, String username, String password) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView();
        if ("".equals(username)|| "".equals(password)) {
            modelAndView.setViewName("login");
            modelAndView.addObject("username", username);
            modelAndView.addObject("msg", "账号密码不能为空");
        } else {
            String shaPassword = encode(password);
            User user = userService.getUserByUsernameAndPassword(username,shaPassword);
            System.out.println(user);
            if (user == null) {
                modelAndView.setViewName("login");
                modelAndView.addObject("username", username);
                modelAndView.addObject("msg", "账号密码错误");
            }else {
                if(user.getIsAdmin() == 1){
                    String token = TokenUtil.createToken(username);
                    CookieUtil.add(response,token);
                    userService.updateTokenByUsername(token,username);
                    modelAndView.setViewName("upload");
                }else {
                    modelAndView.setViewName("login");
                    modelAndView.addObject("username", username);
                    modelAndView.addObject("msg", "对不起,您不是管理员");
                }
            }
        }
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping("/out")
    public void delete(HttpServletRequest request,HttpServletResponse response){
        //System.out.println("out");
        Cookie []cookies=request.getCookies();
        if(cookies!=null){
            for(Cookie cookie: cookies){
                if(cookie!=null){
                    if("token".equals(cookie.getName())){
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }
    }



    @ResponseBody
    @RequestMapping("/get/avatar/{username}")
    public String getUserAvatar(@PathVariable String username) {
        if (username == null) {
            return prefix + "b4c84d78e0659f077746b7ccb9ca5624";
        }
        String avatar = userService.getAvatar(username);
        if (avatar == null) {
            return prefix + "b4c84d78e0659f077746b7ccb9ca5624";
        } else {
            String[] arry = avatar.split("/");
            if (arry.length >= 2) {
                return prefix + avatar.split("/")[2];
            }
            return prefix + "b4c84d78e0659f077746b7ccb9ca5624";
        }
    }

    @RequestMapping("/admin_upload")
    public ModelAndView upload(@RequestParam("file")MultipartFile uploadfile, @RequestParam("spath")String spath,
                         @RequestParam("uploadUserId")Integer uploadUserId, @RequestParam("collegeName")String collegeName,
                         HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload");
        try{
            String ip = NetworkUtil.getIpAddress(request);
            if(ip.equals("0:0:0:0:0:0:0:1")){
                ip = "120.0.0.1";
            }
            /* public Files(Integer size, String md5, String ip, Integer uploadUserId, String spath) {*/
            String md5 = DigestUtils.md5Hex(uploadfile.getInputStream());
            List<FileDetail> fileDetails= fileService.getListFileByMD5(md5);
            if(!fileDetails.toString().equals("[]")){ // 判断是否为空
       //         System.out.println(fileDetails);
                modelAndView.addObject("msg","文件已存在");
                return modelAndView;
            }
            Files file = new Files(uploadfile.getSize(),md5,ip,uploadUserId,spath);
            Integer collegeId = collegeService.selectIdByCollegeName(collegeName);
            //System.out.println("开始上传");
            uploadFileService.uploadToOss(uploadfile,file,collegeId);
            modelAndView.addObject("msg","上传成功");
        }catch (Exception e){
            e.printStackTrace();
            modelAndView.addObject("msg","上传失败");
        }

        //  System.out.println(file);
        //  System.out.println(collegeId);
        //  System.out.println(file);
        return modelAndView;
    }
}
