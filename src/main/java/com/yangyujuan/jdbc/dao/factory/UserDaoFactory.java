package com.yangyujuan.jdbc.dao.factory;


import java.io.InputStream;
import java.util.Properties;
import com.yangyujuan.jdbc.dao.UserDao;

public class UserDaoFactory {
	private static UserDao dao = null;
	private static UserDaoFactory instance = new UserDaoFactory();

	private UserDaoFactory() {
		try {
			Properties prop = new Properties();
			InputStream inStream = UserDaoFactory.class.getClassLoader()
					.getResourceAsStream("daoconfig.properties");
			prop.load(inStream);
			String daoClass = prop.getProperty("userDaoClass");
			Class clazz = Class.forName(daoClass);
			dao = (UserDao) clazz.newInstance();
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static UserDaoFactory getInstance() {
		return instance;
	}

	public UserDao getDao() {
		return dao;
	}
}
