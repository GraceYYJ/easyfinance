package com.yangyujuan.jdbc.dao;

import java.util.ArrayList;

import com.yangyujuan.jdbc.domain.News;

public interface NewsDao {
	//返回新插入数据的ID
	public int addNews(News news);

	public News getNews(int newsId);
	
	public ArrayList<News> getNewsList(int pageNum);//得到指定页数的新闻列表

	public boolean update(News news);

	public boolean delete(News news);
	
	public boolean delete(int newsId);
	
	public int getPageCount();
	
	public int getNewsCount();//得到新闻总个数
	
	public ArrayList<News> getAllNewsList();//得到所有的新闻列表
}
