package com.gracyya.service;

import com.gracyya.model.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/16.
 */
public interface NewsService {
    public List getAllNews();
    public ArrayList<News> getNewsList(int pageNum);
    public int getPageCount();
    public int getNewsCount();
    public News selectByPrimaryKey(Long id);
    public int deleteByPrimaryKey(Long id);
    public int insert(News record);
    public int updateByPrimaryKey(News record);
    public int updateNews(Long id, String title, String source, String pubtime, String bodytext);
}