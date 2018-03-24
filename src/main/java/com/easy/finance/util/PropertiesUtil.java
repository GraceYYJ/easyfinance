package com.easy.finance.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.springframework.stereotype.Component;

@Component("myPropertiesUtil")
public class PropertiesUtil {
	private static Properties properties=null;
	
	static{
		try {
			properties=new Properties();
			InputStream is = PropertiesUtil.class.getClassLoader()
					.getResourceAsStream("dbcpconfig.properties");
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String key){
		return properties.getProperty(key);
	}
}
