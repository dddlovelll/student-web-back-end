package com.qmkl.Util;

import com.alibaba.fastjson.JSON;
import com.qmkl.entity.ResponseData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class IOUtil {

    // 通过request请求获取json数据
    public static String GetInputStreamString(HttpServletRequest request) throws IOException {
        StringBuffer data = new StringBuffer();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null)
            data.append(line);
        //System.out.println(data.toString());
        return data.toString();
    }

    public static void OutResponseData(HttpServletResponse response, ResponseData responseData) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println(JSON.toJSONString(responseData));
        out.flush();
        out.close();
    }
}
