package com.qmkl.controller;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.ClientException;
import com.qmkl.entity.ResponseData;
import com.qmkl.entity.Sms;
import com.qmkl.entity.User;
import com.qmkl.service.SmsService;
import com.qmkl.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import static com.qmkl.Util.IOUtil.GetInputStreamString;
import static com.qmkl.Util.IOUtil.OutResponseData;
import static com.qmkl.Util.NetworkUtil.getIpAddress;
import static com.qmkl.Util.SmsUtil.SendVerificationCode;
import static com.qmkl.Util.TokenUtil.createSmsToken;
import static com.qmkl.Util.TokenUtil.createToken;
import static com.qmkl.Util.VerCodeUtil.CreateRandomNum;


@RestController
@RequestMapping(value = "/sms", method = RequestMethod.POST)
public class SmsController {

    @Resource
    private SmsService smsService;

    @Resource
    private UserService userService;


    //OK
    @RequestMapping("/send")
    public ResponseData insertSms(HttpServletRequest request, HttpServletResponse response) throws IOException, ClientException {
        ResponseData responseData = null;
        try {
            String date = GetInputStreamString(request);
            Map<String, String> map = JSON.parseObject(date, Map.class);
            responseData = new ResponseData();
            String phone = map.get("phone");
            String msg = map.get("msg");
            User user = userService.selectUserByPhone(phone);
            if (("修改密码".equals(msg) && user!=null)|| ("注册".equals(msg) && user == null)) {
                String code = CreateRandomNum();
                Sms sms = new Sms();
                sms.setMsg(msg);
                sms.setCreatedAt(new Date());
                sms.setVerCode(code);
                sms.setToken(createSmsToken());
                sms.setIp(getIpAddress(request));
                sms.setPhone(phone);
                smsService.insertSms(sms);
                responseData.setData(sms.getToken());
                SendVerificationCode(phone, code,msg);
            } else if ("注册".equals(msg) && user != null) {
                responseData.setStatusOther("手机号已注册");
            }else if ("修改密码".equals(msg) && user == null) {
                responseData.setStatusOther("该用户不存在");
            } else {
                responseData.setStatusError();
            }
        } catch (Exception e) {
            responseData.setStatusOther("发送短信失败");
        }
        return responseData;
    }

}
