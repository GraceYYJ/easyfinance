package com.easy.finance.jdbc.dao.factory;

import java.io.InputStream;
import java.util.Properties;

import com.easy.finance.jdbc.dao.NewsDao;

public class NewsDaoFactory {
	private static NewsDao newsDao = null;
	private static NewsDaoFactory instance = new NewsDaoFactory();

	private NewsDaoFactory() {
		try {
			Properties prop = new Properties();
			//双亲委派模型，获取类加载器，获取资源
			InputStream inStream = NewsDaoFactory.class.getClassLoader()
					.getResourceAsStream("daoconfig.properties");
			prop.load(inStream);
			String newsDaoClass = prop.getProperty("newsDaoClass");
			Class clazz = Class.forName(newsDaoClass);
			newsDao = (NewsDao) clazz.newInstance();
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static NewsDaoFactory getInstance() {
		return instance;
	}

	public NewsDao getDao() {
		return newsDao;
	}
}
