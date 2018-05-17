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
    @Autowired
    private NewsMapper newsMapper;

    public List getAllNews(){return newsMapper.getAllNews();}
    public ArrayList<News> getNewsList(int pageNum){return (ArrayList<News>)newsMapper.getNewsList(pageNum);}
}
