package com.qmkl.Exception;
import com.qmkl.entity.ResponseData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


// 全局处理异常
//@RestController
//@ControllerAdvice
public class AppWideExceptionHandler {

    //@ExceptionHandler(NoLoginException.class)
    public ResponseData handlerNoLoginException(){
        ResponseData responseData = new ResponseData();
        responseData.setStatusNoLogin();
        return responseData;
    }
}
