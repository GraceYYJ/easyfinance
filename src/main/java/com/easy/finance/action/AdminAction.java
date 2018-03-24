package com.easy.finance.action;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.easy.finance.jdbc.dao.UserDao;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.easy.finance.jdbc.dao.NewsDao;
import com.easy.finance.jdbc.dao.factory.NewsDaoFactory;
import com.easy.finance.jdbc.dao.factory.UserDaoFactory;
import com.easy.finance.jdbc.domain.News;
import com.easy.finance.jdbc.domain.User;


/**
 * @author Administrator
 *
 */
public class AdminAction extends ActionSupport implements ServletRequestAware{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static NewsDao newsDao = NewsDaoFactory.getInstance().getDao();
	private static UserDao userDao = UserDaoFactory.getInstance().getDao();
	 
	 public AdminAction(){
	 }
	 
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
	}
	
	private String pageNum="1";
    public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	
	
	public String adminIndex(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
//	 		int page = Integer.valueOf(request.getParameter("pageNum"));//当前页数
			int page = Integer.valueOf(pageNum);
	 		int iPageCount = newsDao.getPageCount();//总页数
    		ArrayList<News> listNews = newsDao.getNewsList(page);
    		ActionContext ct=ActionContext.getContext();
     		ct.put("news", listNews);
     		ct.put("gCurPageNum", page);
     		ct.put("iPageCount", iPageCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
	}
	
 	public String modify(){
 		HttpServletRequest request = ServletActionContext.getRequest();
 		int id = Integer.valueOf(request.getParameter("id"));
 		News news = newsDao.getNews(id);
 		ActionContext ct=ActionContext.getContext();
 		ct.put("news", news);
 		return SUCCESS; 
 	}
 	
 	public String delete(){
 		HttpServletRequest request = ServletActionContext.getRequest();
 		int id = Integer.valueOf(request.getParameter("id"));
 		if(newsDao.delete(id)){
 			return SUCCESS; 
 		}else{
 			return SUCCESS; 
 		}
 	}
 	
 	private String result;
 	private String id;
    private String title;
    private String source;
    private String time;
    private String bodytext;
    
 	public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBodytext() {
		return bodytext;
	}

	public void setBodytext(String bodytext) {
		this.bodytext = bodytext;
	}

	
 	
 	public String updateNews(){
 		try {
 			News news = new News();
 			news.setId(Integer.valueOf(id.trim()));
 			news.setPubTime(time);
 			news.setSource(source);
 			news.setTitle(title);
 			news.setBodytext(bodytext);
 			result = "success";
 			if(!newsDao.update(news)){
 				result = "fail";
 			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
 	}
 	
 	public String detail(){
 		HttpServletRequest request = ServletActionContext.getRequest();
 		int id = Integer.valueOf(request.getParameter("id"));
 		News news = newsDao.getNews(id);
 		ActionContext ct=ActionContext.getContext();
 		ct.put("news", news);
 		return SUCCESS; 
 	}

 	private String username ;
 	private String password ;
 	
 	
 	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {                                  
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
 	
 	public String login(){
// 		System.out.println("username:"+username + ",password:" + password);
 		if(username==null||"".equals(username)){
 			return ERROR;
 		}
 		User user = userDao.getByName(username);
 		if(user!=null){
// 			System.out.println(dao.getPasswordByName(username));
 			if(userDao.getPasswordByName(username).equals(password)){
// 				User seuser = new User(username,password);
 	 			ActionContext ct=ActionContext.getContext();
 	 		 	Map<String,Object> session = ct.getSession();
 	 		 	session.put("user", user);
 	 			return SUCCESS; 
 			}else{
 				return ERROR; 
 			}
 		}else{
 			return ERROR;
 		}
 	}
 	
 	

 	
}
