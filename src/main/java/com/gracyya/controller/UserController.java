package com.gracyya.controller;

import com.gracyya.model.Myuser;
import com.gracyya.model.News;
import com.gracyya.service.NewsService;
import com.gracyya.service.UserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/15.
 */
@Controller
@RequestMapping("/admin")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    NewsService newsService;
    @RequestMapping("/getUser/{id}")
    public ModelAndView getUser(@PathVariable Long id){
        ModelAndView modelandview =new ModelAndView();
        Myuser user=userService.getUserById(id);
        modelandview.addObject("user",user);
        modelandview.setViewName("/test/getUser");
        return modelandview;
    }
    @RequestMapping("/loginindex")
    public ModelAndView loginindex(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/toutiao/adminlogin");
        return modelAndView;
    }
    @RequestMapping(value = "/index",produces = "text/html;charset=UTF-8")
    public ModelAndView login(HttpServletRequest request){
        String username = request.getParameter("username");// 接收id（通过页面输入框的name=id接收到）
        String pwd = request.getParameter("password");// 接收pwd（通过页面输入框的name=pwd接收到）
        Myuser user=userService.getByName(username);
        String url;
        HttpSession session = getSession();
        if (user != null && pwd.equals(user.getPassword())) {
            url = "/admin/index";
            session.setAttribute("user", user);
        } else {
            url = "/toutiao/adminlogin";
        }
        ModelAndView view = new ModelAndView(url);
        return view;
    }
    @RequestMapping("/detail/{id}")
    public ModelAndView updateNews(@PathVariable Long id,Model model){
        News news=newsService.selectByPrimaryKey(id);
        //ModelAndView modelandview =new ModelAndView();
        //modelandview.addObject("news",news);
        //modelandview.setViewName("/admin/detail");
        model.addAttribute("news", news);
        ModelAndView modelandview =new ModelAndView("/admin/detail");
        return modelandview;
    }






    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {}
        return session;
    }
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }
}
