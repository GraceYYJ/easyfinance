package com.yangyujuan.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.yangyujuan.jdbc.dao.UserDao;
import com.yangyujuan.jdbc.dao.factory.UserDaoFactory;
import com.yangyujuan.jdbc.domain.User;

public class LoginInterceptor extends AbstractInterceptor{

	//拦截器名称
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();
		UserDao dao = UserDaoFactory.getInstance().getDao();
		Map<String, Object> session = context.getSession();
		User user = (User) session.get("user");
		if(user!=null&&user.getPassword().equals(dao.getPasswordByName(user.getName()))){
			return invocation.invoke();
		}
		return Action.ERROR;
	}
	
	
	
}
