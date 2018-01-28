package com.yangyujuan.jdbc.dao.factory;


import java.io.InputStream;
import java.util.Properties;
import com.yangyujuan.jdbc.dao.ProcessorDao;

public class ProcessorDaoFactory {
	private static ProcessorDao dao = null;
	private static ProcessorDaoFactory instance = new ProcessorDaoFactory();

	private ProcessorDaoFactory() {
		try {
			Properties prop = new Properties();
			InputStream inStream = ProcessorDaoFactory.class.getClassLoader()
					.getResourceAsStream("daoconfig.properties");
			prop.load(inStream);
			String daoClass = prop.getProperty("processorDaoClass");
			Class clazz = Class.forName(daoClass);
			dao = (ProcessorDao) clazz.newInstance();
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static ProcessorDaoFactory getInstance() {
		return instance;
	}

	public ProcessorDao getDao() {
		return dao;
	}
}
