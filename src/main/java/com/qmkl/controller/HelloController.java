package com.qmkl.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {


    @RequestMapping("/hello")
    public String hello() {
        return "hello,this is a springBoot demo";
    }

    @RequestMapping("/forward")
    public ModelAndView forward(HttpServletRequest request, HttpServletResponse response) {
        String viewName = request.getParameter("viewName");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(viewName);
        return modelAndView;
    }


}
