package com.easy.finance.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.easy.finance.jdbc.dao.NewsDao;
import com.easy.finance.jdbc.dao.factory.NewsDaoFactory;
import com.easy.finance.jdbc.domain.News;

public class NewsAction extends News implements ServletRequestAware,
Action {
	private HttpServletRequest request;

	public NewsAction(){}
	
	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}

	public String showNews() throws Exception {
		NewsDao newsDao = NewsDaoFactory.getInstance().getDao();
		News news = new News();
		news = newsDao.getNews(1);
		//ServletActionContext.getRequest().setAttribute("list", news);
		ArrayList<News> listNews = new ArrayList<News>();
		listNews.add(news);
		request.setAttribute("list", listNews);
		return SUCCESS;
	}
	

}
