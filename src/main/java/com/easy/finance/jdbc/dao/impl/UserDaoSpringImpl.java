package com.easy.finance.jdbc.dao.impl;


import com.easy.finance.jdbc.dao.UserDao;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.easy.finance.jdbc.JdbcUtils;
import com.easy.finance.jdbc.domain.User;

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
