package com.yangyujuan.jdbc.dao.impl;


import java.util.ArrayList;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.yangyujuan.jdbc.dao.NewsDao;
import com.yangyujuan.jdbc.JdbcUtils;
import com.yangyujuan.jdbc.domain.News;

public class NewsDaoSpringImpl implements NewsDao {
	private SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(
			JdbcUtils.getDataSource());

	private static final int PAGESIZE = 10;
	
	@Override
	public boolean delete(News news) {
		String sql = "delete from news where id=?";
		//受影响的行数是否大于0
		return (this.simpleJdbcTemplate.update(sql, news.getId())>0);
	}
	
	@Override
	public boolean delete(int newsId) {
		String sql = "delete from news where id=?";
		return (this.simpleJdbcTemplate.update(sql, newsId)>0);
	}
	
	@Override
	public boolean update(News news) {
		String sql = "update news set title=:title, bodytext=:bodytext, source=:source,pubTime=:pubTime where id=:id ";
		return (this.simpleJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(news))>0);
	}

	@Override
	public int addNews(News news) {
		String sql = "insert into News (title,bodytext,source,pubTime) values (:title,:bodytext,:source,:pubTime)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(news);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int iId = -1;
		try{
			this.simpleJdbcTemplate.getNamedParameterJdbcOperations().update(sql,param, keyHolder);
		}catch(Exception e){
			return iId;
		}
		iId = keyHolder.getKey().intValue();
		news.setId(keyHolder.getKey().intValue());
		return iId;
	}

	@Override
	public News getNews(int userId) {
		String sql = "select id, title,bodytext,source,pubTime,visits  from News where id=?";
		return this.simpleJdbcTemplate.queryForObject(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(News.class),
				userId);
	}

	@Override
	public ArrayList<News> getNewsList(int pageNum) {
		int num1 = pageNum*PAGESIZE;
		int num2 = (pageNum-1)*PAGESIZE+1;
		String sql = "select * from News order by pubTime desc limit " + (pageNum-1) * PAGESIZE+","+PAGESIZE;

//		String sql = "SELECT TOP " + PAGESIZE + " * FROM "+
//	"( SELECT TOP ("+num1+") ROW_NUMBER() OVER (ORDER BY pubTime DESC) AS RowNum, * FROM News ) AS tempTable " +
//				" WHERE RowNum BETWEEN " + num2 + " AND "+num1 +" ORDER BY RowNum";
		ArrayList<News> newsList = new ArrayList<News>();
		newsList = (ArrayList<News>) this.simpleJdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(News.class));
		return newsList;
	}

	/*
	 * 得到News表的页面数，每页10条记录
	 */
	@Override
	public int getPageCount(){
		String sql = "SELECT COUNT(*) FROM News";
		int iPageCount = this.simpleJdbcTemplate.queryForInt(sql)/PAGESIZE+1;
		return iPageCount;
	}

	@Override
	public int getNewsCount(){
		String sql = "SELECT COUNT(*) FROM News";
		int iCount = this.simpleJdbcTemplate.queryForInt(sql);
		return iCount;
	}
	
	@Override
	public ArrayList<News> getAllNewsList(){
		String sql = " SELECT * FROM News ";
		ArrayList<News> newsList = new ArrayList<News>();
		newsList = (ArrayList<News>) this.simpleJdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(News.class));
		return newsList;
	}
}
