package com.gracyya.controller;

import com.gracyya.lucene.LuceneService;
import com.gracyya.model.Myuser;
import com.gracyya.model.News;
import com.gracyya.service.NewsService;
import com.gracyya.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.gracyya.model.ViewNews;
import net.sf.json.JSONArray;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import static com.gracyya.controller.UserController.getSession;

/**
 * Created by Administrator on 2018/5/16.
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Resource
    NewsService newsService;
    @Resource
    UserService userService;

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/toutiao/index");
        return modelAndView;
    }

    @RequestMapping(value = "/getNewsListJson", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getNewsListJson(@RequestParam("pageNum") Integer pageNum) {
        int pageNum1 = pageNum * 10 - 10;
        ArrayList<News> news = newsService.getNewsList(pageNum1);
        JSONArray json = JSONArray.fromObject(news);
        String result = json.toString();
        return result;
    }

    @RequestMapping(value = "/getNewsPageCount")
    @ResponseBody
    public String getNewsPageCount() {
        int iPageCount = newsService.getPageCount();
        Map<String, Integer> Page = new HashMap<String, Integer>();
        Page.put("pageCount", iPageCount);
        JSONObject json = JSONObject.fromObject(Page);
        String result = json.toString();//给result赋值，传递给页面
        return result;
    }

    @RequestMapping(value = "/getSingleNewsJson", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getSingleNewsJson(@RequestParam(value = "newsid") Long id) {
        News news = newsService.selectByPrimaryKey(id);
        JSONObject json = JSONObject.fromObject(news);
        String result = json.toString();
        return result;
    }

    @RequestMapping(value = "/indexSearch", produces = "text/html;charset=UTF-8")
    public ModelAndView indexSearch(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        String key="china";
        try {
            key= new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String searchPagenum = request.getParameter("searchPagenum");
        if(searchPagenum==null) searchPagenum="1";
        LuceneService luceneService = new LuceneService();
        ArrayList<ViewNews> listNews = null;
        try {
            listNews = luceneService.search(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int allPageNum = 0;//总页数
        int iNewsSize = listNews.size();
        if (iNewsSize > 0)
            allPageNum = iNewsSize / 10 + 1;
        int pagenum = Integer.valueOf(searchPagenum);
        ModelAndView modelAndView = new ModelAndView();
        if (pagenum < allPageNum) {
            List<ViewNews> listNewsPage = listNews.subList(10 * (pagenum - 1), 10 * (pagenum));
            modelAndView.addObject("list", listNewsPage);
        } else if (pagenum == allPageNum) {
            List<ViewNews> listNewsPage = listNews.subList(10 * (pagenum - 1), iNewsSize);
            modelAndView.addObject("list", listNewsPage);
        }
        modelAndView.addObject("keyword", key);
        modelAndView.addObject("pagenum", pagenum);
        modelAndView.addObject("allPageNum", allPageNum);
        modelAndView.setViewName("/toutiao/search");
        return modelAndView;
    }

    @RequestMapping(value = "/hotwordindex")
    public ModelAndView hotwordindex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/toutiao/d3char");
        return modelAndView;
    }

    @RequestMapping(value = "/getHotWord",produces ="text/html;charset=UTF-8")
    @ResponseBody
    public String getHotWord() {
        String result="";
        try {
            LuceneService luceneservice = new LuceneService();
            result = luceneservice.getHotWordJson();//给result赋值，传递给页面
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    //登录页面
    @RequestMapping("/loginindex")
    public ModelAndView loginindex(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/toutiao/adminlogin");
        return modelAndView;
    }
    //登录校验，成功后进入管理员首页（新闻管理页面）
    @RequestMapping(value = "/login",produces = "text/html;charset=UTF-8")
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
    /*    @RequestMapping(value = "/updataHotWord")
    @ResponseBody
    public String updataHotWord() {
        try {
            LuceneService luceneservice = new LuceneService();
            luceneservice.getIDF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }*/

}
