package com.yangyujuan.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yangyujuan.jdbc.dao.NewsDao;
import com.yangyujuan.jdbc.dao.ProcessorDao;
import com.yangyujuan.jdbc.dao.UserDao;
import com.yangyujuan.jdbc.dao.factory.NewsDaoFactory;
import com.yangyujuan.jdbc.dao.factory.ProcessorDaoFactory;
import com.yangyujuan.jdbc.dao.factory.UserDaoFactory;
import com.yangyujuan.jdbc.domain.News;
import com.yangyujuan.jdbc.domain.Processor;
import com.yangyujuan.jdbc.domain.User;
import com.yangyujuan.spider.NewsSpider;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Administrator
 *
 */
public class SpiderAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private static NewsDao newsDao = NewsDaoFactory.getInstance().getDao();
	 
	private static ProcessorDao processorDao = ProcessorDaoFactory.getInstance().getDao();
	public SpiderAction(){}
	 
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
	
	
	public String spiderIndex(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
//	 		int page = Integer.valueOf(request.getParameter("pageNum"));//当前页数
			int page = Integer.valueOf(pageNum);
	 		int iPageCount = processorDao.getPageCount();//总页数
    		List<Processor> list = processorDao.getList(page);
    		ActionContext ct=ActionContext.getContext();
     		ct.put("list", list);
     		ct.put("gCurPageNum", page);
     		ct.put("iPageCount", iPageCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
 	
 	//update需要的数据
 	private String result;
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

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getStarturl() {
		return starturl;
	}

	public void setStarturl(String starturl) {
		this.starturl = starturl;
	}

	public String getLinkstr() {
		return linkstr;
	}

	public void setLinkstr(String linkstr) {
		this.linkstr = linkstr;
	}

	public String getBodytextstr() {
		return bodytextstr;
	}

	public void setBodytextstr(String bodytextstr) {
		this.bodytextstr = bodytextstr;
	}

	public String getPubtimestr() {
		return pubtimestr;
	}

	public void setPubtimestr(String pubtimestr) {
		this.pubtimestr = pubtimestr;
	}

	public String getSourcestr() {
		return sourcestr;
	}

	public void setSourcestr(String sourcestr) {
		this.sourcestr = sourcestr;
	}

	public String getTitlestr() {
		return titlestr;
	}

	public void setTitlestr(String titlestr) {
		this.titlestr = titlestr;
	}
	
	public String getHelpurlstr() {
		return helpurlstr;
	}

	public void setHelpurlstr(String helpurlstr) {
		this.helpurlstr = helpurlstr;
	}

	private String id;
 	private String sitename;
	private String domain;
	private String starturl;
	private String linkstr;
	private String bodytextstr;
	private String pubtimestr;
	private String sourcestr;
	private String titlestr;
	private String helpurlstr;
    //end

	
 	
 
 	
 

	public String detail(){
 		HttpServletRequest request = ServletActionContext.getRequest();
 		int id = Integer.valueOf(request.getParameter("id"));
 		News news = newsDao.getNews(id);
 		ActionContext ct=ActionContext.getContext();
 		ct.put("news", news);
 		return SUCCESS; 
 	}

 	
 	public String startSpider(){
 		NewsDao newsDao = NewsDaoFactory.getInstance().getDao();
 		int iBeginCount = newsDao.getNewsCount();
 		int iProId = Integer.valueOf(id.trim());
		Processor processor = new Processor();
		processor = processorDao.getById(iProId);
 		NewsSpider spider = new NewsSpider();
		spider.startSpider(processor);
		int iEndCount = newsDao.getNewsCount();
		int iAddCount = iEndCount - iBeginCount;
		result = String.valueOf(iAddCount);
		return SUCCESS;
 	}
 	
 	
	public String modifySpider(){
		ProcessorDao proDao = ProcessorDaoFactory.getInstance().getDao();
 		HttpServletRequest request = ServletActionContext.getRequest();
 		int id = Integer.valueOf(request.getParameter("id"));
 		Processor processor = proDao.getById(id);
 		ActionContext ct=ActionContext.getContext();
 		ct.put("obj", processor);
 		return SUCCESS; 
 	}
	
	public String updateSpider(){
 		try {
 			Processor obj = new Processor();
 			obj.setId(Integer.valueOf(id.trim()));
 			obj.setDomain(domain);
 			obj.setBodytextstr(bodytextstr);
 			obj.setLinkstr(linkstr);
 			obj.setPubtimestr(pubtimestr);
 			obj.setSitename(sitename);
 			obj.setSourcestr(sourcestr);
 			obj.setStarturl(starturl);
 			obj.setTitlestr(titlestr);
 			obj.setHelpurlstr(helpurlstr);
 			result = "success";
 			if(!processorDao.update(obj)){
 				result = "fail";
 			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
 	}
 	 
	public String addSpider(){
 		try {
 			Processor obj = new Processor();
 			obj.setDomain(domain);
 			obj.setBodytextstr(bodytextstr);
 			obj.setLinkstr(linkstr);
 			obj.setPubtimestr(pubtimestr);
 			obj.setSitename(sitename);
 			obj.setSourcestr(sourcestr);
 			obj.setStarturl(starturl);
 			obj.setTitlestr(titlestr);
 			obj.setHelpurlstr(helpurlstr);
 			processorDao.add(obj);
 			result = "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
 	}
	
	public String deleteSpider(){
 		try {
 			int deleteId=-1;
 			deleteId = Integer.valueOf(id.trim());
 			result = "success";
 			if(!processorDao.delete(deleteId)){
 				result = "fail";
 			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
 	}
	
}
