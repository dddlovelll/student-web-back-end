package com.qmkl.controller;
import com.qmkl.entity.ResponseData;
import com.qmkl.service.CollegeService;
import com.qmkl.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import static com.qmkl.Util.IOUtil.OutResponseData;

@RestController
@RequestMapping(value = "/college")
public class CollegeController {

    @Resource
    private CollegeService collegeService;

    @Resource
    private UserService userService;


    @RequestMapping( "/list")
    public ResponseData list(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        List<String> list = collegeService.listAllName();
        responseData.setData(list);
        return responseData;
    }

}
