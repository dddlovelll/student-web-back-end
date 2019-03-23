package com.qmkl.controller;

import com.qmkl.entity.Advert;
import com.qmkl.entity.FileDetail;
import com.qmkl.service.AdvertService;
import com.qmkl.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import static com.qmkl.Util.NetworkUtil.GetType;
import static com.qmkl.Util.NetworkUtil.getIpAddress;
import static com.qmkl.Util.OssUtil.CreateUrlViewOnline;
import static com.qmkl.Util.OssUtil.CreateUrlViewOnlinePdfOrImg;
import static com.qmkl.Util.OssUtil.CreateUrlWithFileName;

@Controller
@RequestMapping(value = "/dir",method = RequestMethod.GET)
public class DirUrl{

    @Resource
    private AdvertService advertService;

    @Resource
    FileService fileService;

    @RequestMapping("/protocol/{remark}")
    public ModelAndView protocol(@PathVariable String remark){
        ModelAndView modelAndView = new ModelAndView();
        if(remark.equals("policy") || remark.equals("agreement")){
            modelAndView.setViewName(remark);
            return modelAndView;
        }else{
            return null;
        }

    }


    @RequestMapping("/url/{remark}")
    public String url(@PathVariable("remark")String remark, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Advert advert = advertService.getAdvertByRemark(remark);
        if(advert!=null){
            try{
                String ip = getIpAddress(request);
                String type = GetType(request);
                advertService.saveAdDate(ip,new Date(),type,remark);
                return "redirect:"+advert.getUrl();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return "redirect:https://finalexam.cn/";
    }

    @RequestMapping(value = "/download/file/{md5}/{id}",method = RequestMethod.GET)
    public String downloadPC(@PathVariable Integer id,@PathVariable String md5 ,HttpServletRequest HttpRequest, HttpServletResponse response) throws ParseException, IOException {
        try{
            if(id!=null){
                FileDetail fileDetail = fileService.getFileByIdAndMd5(id,md5);
                if(fileDetail!=null){
                    String []arry = fileDetail.getName().split("/");
                    String fileName = arry[arry.length-1];
                    String url = CreateUrlWithFileName(fileDetail.getMd5(),fileName);
                    return "redirect:"+url;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:https://finalexam.cn/";
    }

    @RequestMapping(value = "/online/file/{md5}/{id}/{onlineName}",method = RequestMethod.GET)
    public String viewOnline(@PathVariable String onlineName,@PathVariable Integer id,@PathVariable String md5 ,HttpServletRequest HttpRequest, HttpServletResponse response) throws ParseException, IOException {
        try{
            if(id!=null){
                FileDetail fileDetail = fileService.getFileByIdAndMd5(id,md5);
                if(fileDetail!=null){
                    String []arry = fileDetail.getName().split("/");
                    String fileName = arry[arry.length-1];
                    String suffix = onlineName.split("\\.")[1];
                    boolean flag = suffix.equals("pdf") || suffix.equals("jpg") || suffix.equals("png");
                    String url = null;
                    if(flag){
                        url = CreateUrlViewOnlinePdfOrImg(fileDetail.getMd5(),fileName,suffix);
                    }else {
                        url = CreateUrlViewOnline(fileDetail.getMd5(),fileName);
                    }
                    return "redirect:"+url;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:https://finalexam.cn/";
    }


    @RequestMapping(value = "/download/file/ios/{md5}/{spath}",method = RequestMethod.GET)
    public String downloadIos(@PathVariable("spath") String spath,@PathVariable("md5") String md5 ,HttpServletRequest HttpRequest, HttpServletResponse response) throws ParseException, IOException {
        try{
            if(spath!=null){
                if(fileService.getFileBySpathAndMd5(spath,md5)!=null){
                    String url = CreateUrlWithFileName(md5,spath);
                    return "redirect:"+url;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:https://finalexam.cn/";
    }






}
