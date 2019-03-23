package com.qmkl.Util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.DateUtil;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ResponseHeaderOverrides;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OssUtil {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private final static String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    private final static String accessKeyId = "LTAIVDXjdgFVd2Le";
    private final static String accessKeySecret = "S1prFN5Mxixm7riLkSjsqcPq08TcXO";
    private final static String bucketName = "qmkl000";
    //创建存储空间
    private static SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'",Locale.US);


    public static String CreateUrl(String md5) throws ParseException {

//        StringBuilder sb = new StringBuilder(md5);//构造一个StringBuilder对象
//        sb.insert(2, "/");//在指定的位置1，插入指定的字符串
//        md5 = sb.toString();

        //服务器端生成url签名字串
        OSSClient Server  = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE,1);
        Date date = cal.getTime();

        Date expiration = DateUtil.parseRfc822Date(sdf.format(date));

        StringBuilder sb=new StringBuilder(md5);
        //System.out.println("objects/"+sb.toString());
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, "objects/"+sb.insert(3,"/").toString());
       // System.out.println(md5);
        request.setExpiration(expiration);
        URL signedUrl = Server .generatePresignedUrl(request);
        //System.out.println("signed url for getObject: " + signedUrl);
        return signedUrl.toString();
    }



    public static String CreateUrlWithFileName(String md5,String fileName) throws ParseException, UnsupportedEncodingException {
        OSSClient Server  = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE,1);
        Date date = cal.getTime();
        Date expiration = DateUtil.parseRfc822Date(sdf.format(date));
        StringBuilder sb=new StringBuilder(md5);
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, "objects/"+sb.insert(3,"/").toString());
        request.setExpiration(expiration);
        ResponseHeaderOverrides responseHeaderOverrides = new ResponseHeaderOverrides();
        responseHeaderOverrides.setContentDisposition("response-content-disposition=attachment;filename="+fileName);
        request.setResponseHeaders(responseHeaderOverrides);
        URL signedUrl = Server .generatePresignedUrl(request);
        return signedUrl.toString();
    }


    public static String CreateUrlViewOnlinePdfOrImg(String md5,String fileName,String reponseType) throws ParseException, UnsupportedEncodingException {
        OSSClient Server  = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE,1);
        Date date = cal.getTime();
        Date expiration = DateUtil.parseRfc822Date(sdf.format(date));
        StringBuilder sb=new StringBuilder(md5);
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, "objects/"+sb.insert(3,"/").toString());
        request.setExpiration(expiration);
        String suffix = fileName.split("\\.")[1];
        ResponseHeaderOverrides responseHeaderOverrides = new ResponseHeaderOverrides();
        if(reponseType.equals("pdf")){
            responseHeaderOverrides.setContentType("application/"+suffix);
        }else {
            responseHeaderOverrides.setContentType("image/"+suffix);
        }
        responseHeaderOverrides.setContentDisposition("response-content-disposition=inline;filename="+fileName);
        //  responseHeaderOverrides.setContentDisposition("response-content-disposition=attachment;filename=1."+suffix);
        request.setResponseHeaders(responseHeaderOverrides);
        URL signedUrl = Server .generatePresignedUrl(request);
       // System.out.println( signedUrl.toString());
        return signedUrl.toString();
    }

   // public static String urlPrefix = "https://qmkl000.oss-cn-shenzhen.aliyuncs.com/objects/003/77f60f30276ee0f808b3f294c9ba8";
   public static String urlPrefix = "https://qmkl000.oss-cn-shenzhen.aliyuncs.com/";
    public static String CreateUrlViewOnline(String md5,String fileName) throws ParseException, UnsupportedEncodingException {
        StringBuilder sb=new StringBuilder(md5);
      //  System.out.println(urlPrefix +"objects/"+sb.insert(3,"/").toString());
        return urlPrefix +"objects/"+sb.insert(3,"/").toString();
    }


    public static void upload(MultipartFile file,String md5) throws IOException {
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流。
        InputStream inputStream = file.getInputStream();
        String str1 = md5.substring(0,3);
        String str2 = md5.substring(3);
       // System.out.println(str1+"/"+str2);
        ossClient.putObject(bucketName, "objects/"+str1+"/"+str2, inputStream);
        // 关闭OSSClient。
        //System.out.println("上传完成");
        ossClient.shutdown();
    }
}
