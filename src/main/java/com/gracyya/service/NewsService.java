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
}
