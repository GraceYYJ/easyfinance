package com.gracyya.controller;

import com.gracyya.model.News;
import com.gracyya.service.NewsService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

/**
 * Created by Administrator on 2018/5/16.
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Resource
    NewsService newsService;
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/toutiao/index");
        return modelAndView;
    }
    @RequestMapping("/getNewsListJson")
    @ResponseBody
    public String getNewsListJson(@RequestParam("pageNum") Integer pageNum){
        int pageNum1=pageNum*10-10;
        ArrayList<News> news=newsService.getNewsList(pageNum1);
        JSONArray json = JSONArray.fromObject(news);
        String result = json.toString();
        return result;
    }
    @RequestMapping("/getNewsPageCount")
    @ResponseBody
    public String getNewsPageCount(){
        int iPageCount=newsService.getPageCount();
        Map<String,Integer> Page=new HashMap<String,Integer>();
        Page.put("pageCount", iPageCount);
        JSONObject json = JSONObject.fromObject(Page);
        String result = json.toString();//给result赋值，传递给页面
        return result;
    }
}
