package com.gracyya.controller;

import com.gracyya.model.Myuser;
import com.gracyya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/5/15.
 */
@Controller
@RequestMapping("/admin")
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("/getUser/{id}")
    public ModelAndView getUser(@PathVariable Long id){
        ModelAndView modelandview =new ModelAndView();
        Myuser user=userService.getUserById(id);
        modelandview.addObject("user",user);
        modelandview.setViewName("/test/getUser");
        return modelandview;
    }
    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/toutiao/adminlogin");
        return modelAndView;
    }
}
