package com.easy.finance.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.easy.finance.jdbc.domain.News;
import com.easy.finance.jdbc.domain.ViewNews;
import com.easy.finance.lucene.LuceneService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.easy.finance.jdbc.dao.NewsDao;
import com.easy.finance.jdbc.dao.factory.NewsDaoFactory;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

public class NewsJsonAction extends ActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = 1L;
	private  NewsDao newsDao = NewsDaoFactory.getInstance().getDao();
	
//    private HttpServletRequest request;
    private String result;
    
    public NewsJsonAction(){}
    
//    public void setServletRequest(HttpServletRequest arg0) {
//        this.request = arg0;
//    }
    @Override
	public void setServletRequest(HttpServletRequest arg0) {
	}
    
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    
    /**
     * 处理ajax请求
     * @return SUCCESS
     */
    public String excuteAjax(){
         
        try {
            String name = "nametest";
            int age = 18;
            String position = "positiontest";
             
            //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数据
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("name", name);
            map.put("age",age);
            map.put("position", position);
             
            JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
            result = json.toString();//给result赋值，传递给页面
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    private String pageNum;
    public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	/*
     * return:json格式的news列表
     */
    public String getNewsListJson(){
        try {
    		ArrayList<News> listNews = newsDao.getNewsList(Integer.valueOf(pageNum));
    		JSONArray json = JSONArray.fromObject(listNews);
            result = json.toString();//给result赋值，传递给页面
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    private String newsid;
    
    public String getNewsid() {
		return newsid;
	}
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}
	/*
	 * 根据newsId来数据库查找对应的news,返回json格式内容
	 */
	public String getSingleNewsJson(){
        try {
    		News news = newsDao.getNews(Integer.valueOf(newsid));
    		JSONObject json = JSONObject.fromObject(news);
            result = json.toString();//给result赋值，传递给页面
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
	
	/*
	 * 得到News表的页面数，每页10条记录
	 */
	public String getNewsPageCount(){
		 try {
	    		int iPageCount = newsDao.getPageCount();
	    		Map<String,Integer> Page = new HashMap<String,Integer>();
	    		Page.put("pageCount", iPageCount);
	    		JSONObject json = JSONObject.fromObject(Page);
	            result = json.toString();//给result赋值，传递给页面
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return SUCCESS;
	}
	
	
	/**
	 * 
	 */
	private String keyword;
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	
	private String searchPagenum="1";
	
	public String getSearchPagenum() {
		return searchPagenum;
	}
	public void setSearchPagenum(String searchPagenum) {
		this.searchPagenum = searchPagenum;
	}
	/*
     * return:json格式的news列表
     */
    public String getSearchResult(){
        try {
        	LuceneService luceneservice = new LuceneService();
    		ArrayList<ViewNews> listNews = luceneservice.search(keyword);
    		int pagenum = Integer.valueOf(searchPagenum);
    		List<ViewNews> listNewsPage = listNews.subList(0*pagenum, 10*(pagenum+1));
    		JSONArray json = JSONArray.fromObject(listNewsPage);
            result = json.toString();//给result赋值，传递给页面
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
	
    public String indexSearch(){
    	 try {
         	LuceneService luceneservice = new LuceneService();
         	String key= new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
//         	String key = URLDecoder.decode(keyword,"GB2312");
     		ArrayList<ViewNews> listNews = luceneservice.search(key);
     		int allPageNum = 0;//总页数
     		int iNewsSize = listNews.size();
     		if(iNewsSize > 0){
     			allPageNum = iNewsSize/10+1;
     		}
     		int pagenum = Integer.valueOf(searchPagenum);
     		ActionContext ct=ActionContext.getContext();
     		if(pagenum < allPageNum){
     			List<ViewNews> listNewsPage = listNews.subList(10*(pagenum-1), 10*(pagenum));
         		ct.put("list", listNewsPage);
     		}else if(pagenum==allPageNum){
 				List<ViewNews> listNewsPage = listNews.subList(10*(pagenum-1), iNewsSize);
         		ct.put("list", listNewsPage);
 			}
     		ct.put("keyword", key);
     		ct.put("pagenum", pagenum);
     		ct.put("allPageNum", allPageNum);
         } catch (Exception e) {
             e.printStackTrace();
         }
    	 return "list";
    }
    
    
    public String getHotWord(){
        try {
        	LuceneService luceneservice = new LuceneService();
            result = luceneservice.getHotWordJson();//给result赋值，传递给页面
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    public String updataHotWord(){
        try {
        	LuceneService luceneservice = new LuceneService();
        	luceneservice.getIDF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
}
