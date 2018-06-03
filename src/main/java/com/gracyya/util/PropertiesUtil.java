package com.gracyya.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component("myPropertiesUtil")
public class PropertiesUtil {
	private static Properties properties=null;
	
	static{
		try {
			properties=new Properties();
			InputStream is = PropertiesUtil.class.getClassLoader()
					.getResourceAsStream("jdbc.properties");
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String key){
		return properties.getProperty(key);
	}
}
