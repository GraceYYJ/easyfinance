package com.gracyya.dao;

import com.gracyya.model.News;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsMapper {
    public List<News> getAllNews();
    public ArrayList<News> getNewsList(int pageNum);
    public int getNewsCount();
    public int getPageCount();
    public News selectByPrimaryKey(Long id);
    public int deleteByPrimaryKey(Long id);
    public int insert(News record);
    public int updateByPrimaryKey(News record);
}