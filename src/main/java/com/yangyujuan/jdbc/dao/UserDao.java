package com.yangyujuan.jdbc.dao;

import com.yangyujuan.jdbc.domain.User;

public interface UserDao {
	public void add(User bean);

	public User getById(int id);
	
	public User getByName(String name);
	
	public String getPasswordByName(String name);
	
	public boolean update(User bean);

	public boolean delete(User bean);
	
	public boolean delete(int id);
	
}
