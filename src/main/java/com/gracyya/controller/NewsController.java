package com.gracyya.controller;

import com.gracyya.model.News;
import com.gracyya.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/16.
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Resource
    NewsService newsService;
    @RequestMapping("/index")
    public ModelAndView allNews(HttpServletRequest request,Model model){
        List<News> news=new ArrayList<>();
        news=newsService.getAllNews();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/toutiao/index");
        return modelAndView;
    }
}
