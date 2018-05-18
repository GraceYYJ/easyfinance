package com.gracyya.dao;

import com.gracyya.model.News;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsMapper {
    List<News> getAllNews();
    ArrayList<News> getNewsList(int pageNum);
    int getPageCount();
    int deleteByPrimaryKey(Long id);
    int insert(News record);
    News selectByPrimaryKey(Long id);
    int updateByPrimaryKey(News record);
}