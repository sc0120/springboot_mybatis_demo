package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by caoyixian on 2018/6/4.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @RequestMapping(value = "/index")
    public ModelAndView index(ModelAndView mv) {
        String[] strArr = {"aaa","bbb","ccc"};
        logger.info(String.valueOf(strArr.length));
        mv.setViewName("/admin");
        mv.addObject("title","欢迎使用后台!");
        mv.addObject("test","你好!");
        mv.addObject("str",strArr);
        return mv;
    }
}
