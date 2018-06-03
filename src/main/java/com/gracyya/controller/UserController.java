package com.gracyya.controller;

import com.gracyya.model.Myuser;
import com.gracyya.model.News;
import com.gracyya.model.Processor;
import com.gracyya.service.NewsService;
import com.gracyya.service.ProcessorService;
import com.gracyya.service.UserService;
import com.gracyya.spider.NewsSpider;
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
import java.util.List;

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
    @Resource
    ProcessorService processorService;
    //测试方法，获取用户
    @RequestMapping("/getUser/{id}")
    public ModelAndView getUser(@PathVariable Long id){
        ModelAndView modelandview =new ModelAndView();
        Myuser user=userService.getUserById(id);
        modelandview.addObject("user",user);
        modelandview.setViewName("/test/getUser");
        return modelandview;
    }
    //登录页面
    @RequestMapping("/loginindex")
    public ModelAndView loginindex(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/toutiao/adminlogin");
        return modelAndView;
    }
    //登录校验，成功后进入管理员首页（新闻管理页面）
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
    //新闻管理页面的新闻详情
    @RequestMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Long id,Model model){
        News news=newsService.selectByPrimaryKey(id);
        model.addAttribute("news", news);
        ModelAndView modelandview =new ModelAndView("/admin/detail");
        return modelandview;
    }
    //进入具体修改页面
    @RequestMapping("/modify/{id}")
    public ModelAndView modifyindex(@PathVariable Long id,Model model){
        News news=newsService.selectByPrimaryKey(id);
        model.addAttribute("news", news);
        ModelAndView modelandview =new ModelAndView("/admin/modify");
        return modelandview;
    }
    //确认修改
    @RequestMapping(value = "/modifyNews",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String modifyNews(Long id, String title, String source, String pubtime, String bodytext){
        int result=newsService.updateNews(id,title,source,pubtime,bodytext);
        return String.valueOf(result);
    }
    //新闻删除
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(@RequestParam("id") Long id){
        int result=newsService.deleteByPrimaryKey(id);
        return String.valueOf(result);
    }
    //爬虫首页
    @RequestMapping(value = "/spiderIndex")
    public ModelAndView spiderIndex(){
        List<Processor> processors=new ArrayList<>();
        processors=processorService.getAllProcessors();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/admin/spider");
        modelAndView.addObject("processors",processors);
        return modelAndView;
    }
    //添加爬虫
    @RequestMapping(value = "/addSpider")
    @ResponseBody
    public String addSpider(String sitename, String domain, String starturl,
                            String linkstr, String bodytextstr,String pubtimestr,
                            String sourcestr, String titlestr, String helpurlstr){
        int result=processorService.insert(sitename,domain,starturl,linkstr,bodytextstr,pubtimestr,sourcestr,titlestr,helpurlstr);
        return String.valueOf(result);
    }
    //点击爬虫修改
    @RequestMapping(value = "/modifySpider")
    public ModelAndView modifySpider(@RequestParam Long id,Model model){
        Processor processor=processorService.selectByPrimaryKey(id);
        model.addAttribute("processor",processor);
        ModelAndView modelAndView=new ModelAndView("/admin/modSpider");
        return modelAndView;
    }
    //进入爬虫修改
    @RequestMapping(value = "/updateSpider",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateSpider(Long id, String sitename, String domain, String starturl,
                               String linkstr, String bodytextstr,String pubtimestr,
                               String sourcestr, String titlestr, String helpurlstr){
        int result=processorService.updateProcessor(id,sitename,domain,starturl,
                linkstr,bodytextstr,pubtimestr,sourcestr,titlestr,helpurlstr);
        return String.valueOf(result);
    }
    //爬虫规则删除
    @RequestMapping(value = "/deleteSpider")
    @ResponseBody
    public String deleteSpider(@RequestParam Long id){
        int result=processorService.deleteByPrimaryKey(id);
        return String.valueOf(result);
    }
    //开始爬虫
    @RequestMapping(value = "/startSpider")
    public String startSpider(@RequestParam Long id){
        int iBeginCount=newsService.getNewsCount();
        Processor processor=processorService.selectByPrimaryKey(id);
        NewsSpider spider=new NewsSpider();
        spider.startSpider(processor);
        int iEndCount=newsService.getNewsCount();
        int iAddCount=iEndCount-iBeginCount;
        return String.valueOf(iAddCount);
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
