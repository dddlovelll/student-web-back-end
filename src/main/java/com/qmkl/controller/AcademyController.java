package com.qmkl.controller;

import com.alibaba.fastjson.JSON;
import com.qmkl.Exception.NoLoginException;
import com.qmkl.entity.ResponseData;
import com.qmkl.service.AcademyService;
import com.qmkl.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import static com.qmkl.Util.IOUtil.GetInputStreamString;

@RestController
@RequestMapping(value = "/academy", method = RequestMethod.POST)
public class AcademyController {

    @Resource
    private AcademyService academyService;

    @Resource
    private CollegeService collegeService;

    @RequestMapping("/list/college")
    public ResponseData listByCollegeName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData responseData = new ResponseData();
        String date = GetInputStreamString(request);
        Map<String, String> map = JSON.parseObject(date, Map.class);
        if (map.containsKey("collegeName")) {
             Integer collegeId = collegeService.selectIdByCollegeName(map.get("collegeName"));
             List<String> list = academyService.selectAllAcademyNameByCollegeName(collegeId);
             responseData.setData(list);
        }
        return responseData;
    }

}
