package com.yangyujuan.jdbc.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.yangyujuan.jdbc.dao.NewsDao;
import com.yangyujuan.jdbc.dao.UserDao;
import com.yangyujuan.jdbc.JdbcUtils;
import com.yangyujuan.jdbc.domain.News;
import com.yangyujuan.jdbc.domain.User;

public class UserDaoSpringImpl implements UserDao {
	private SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(
			JdbcUtils.getDataSource());

	@Override
	public void add(User bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getById(int id) {
		String sql = "select id,name,password,authority from myUser where id=?";
		return this.simpleJdbcTemplate.queryForObject(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(User.class),
				id);
	}

	@Override
	public User getByName(String name) {
		String sql = "select id,name,password,authority from myUser where name=?";
		User user = null ;
		try{
			user = this.simpleJdbcTemplate.queryForObject(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(User.class),
				name);
		}catch(Exception e){
			
		}
		return user;
	}

	@Override
	public String getPasswordByName(String name) {
		String sql = "select password from myUser where name=?";
		String list = this.simpleJdbcTemplate.queryForObject(sql,String.class, name);
		return list;
	}

	@Override
	public boolean update(User bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
