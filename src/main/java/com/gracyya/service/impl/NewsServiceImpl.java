package com.gracyya.service.impl;

import com.gracyya.dao.NewsMapper;
import com.gracyya.model.News;
import com.gracyya.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/16.
 */
@Service
public class NewsServiceImpl implements NewsService{
    @Autowired(required=true)
    private NewsMapper newsMapper;
    private static final int PAGESIZE = 10;

    public List getAllNews(){return newsMapper.getAllNews();}
    public ArrayList<News> getNewsList(int pageNum){return (ArrayList<News>)newsMapper.getNewsList(pageNum);}
    public int getPageCount(){return newsMapper.getPageCount()/PAGESIZE+1;}
    public int getNewsCount(){return newsMapper.getNewsCount();}
    public News selectByPrimaryKey(Long id){return newsMapper.selectByPrimaryKey(id);}
    public int deleteByPrimaryKey(Long id){return newsMapper.deleteByPrimaryKey(id);}
/*    public Long insert(String title, String source, String pubtime, String bodytext){
        News news=new News();
        news.setTitle(title);
        news.setSource(source);
        news.setPubtime(pubtime);
        news.setBodytext(bodytext);
        return newsMapper.insert(news);
    }*/
    public Long insertNews(News news){
        return newsMapper.insert(news);
    }
    public int updateByPrimaryKey(News record){return newsMapper.updateByPrimaryKey(record);}
    public int updateNews(Long id, String title, String source, String pubtime, String bodytext){
        News news=new News(id,title,source,pubtime,1l,bodytext);
        return newsMapper.updateByPrimaryKey(news);
    }
}
