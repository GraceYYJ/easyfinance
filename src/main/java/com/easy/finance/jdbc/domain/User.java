package com.easy.finance.jdbc.domain;

public class User {

	private int id;
	private String name;
	private String password;
	private String authority;
	
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String username) {
		this.name = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
    public User(){}
	
	public User(String username, String password){
		this.name = username;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "id=" + this.id + " name=" + this.name + 
				 " password=" + this.password ;
	}
	
}
