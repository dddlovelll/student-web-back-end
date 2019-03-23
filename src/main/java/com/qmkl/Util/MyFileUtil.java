package com.qmkl.Util;

import org.apache.commons.codec.digest.DigestUtils;
import java.io.*;
import java.net.URL;

public class MyFileUtil {

    private static final String SaveFilePath = "//home//upload//avatar//"; //服务器上的路径
    private static final String defaultAvatar = "d8f655caa433baff1cd93f1f19d5aaf0.png";

    //private static final String SaveFilePath = "D:\\qmkl\\qmkl下载\\"; //服务器上的路径

    public static String  downloadPicture(String urlList)  {
        String md5 = null;
        try{
            URL url = null;
            url = new URL(urlList);
            if("https".equalsIgnoreCase(url.getProtocol())){
                SslUtils.ignoreSsl();
            }
            md5  = DigestUtils.md5Hex(url.openStream());
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            String imageName = SaveFilePath+md5;
            File path = new File(imageName);
            if(!path.exists()){
                path.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = dataInputStream.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
                byte[] context=output.toByteArray();
                fileOutputStream.write(output.toByteArray());
                dataInputStream.close();
                fileOutputStream.close();
            }
        }catch (Exception e){
            md5 = defaultAvatar;
        }
        return md5;
    }


}
