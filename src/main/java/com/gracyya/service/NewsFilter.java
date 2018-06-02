package com.gracyya.service;

import com.gracyya.model.News;

public class NewsFilter {


	public NewsFilter(){
	}

	/*
	 * 过滤news的函数
	 */
	public static boolean isRightNews(News news){
		boolean flag = true;
		if(isEmptyOrNull(news.getBodytext())||isEmptyOrNull(news.getTitle())||isEmptyOrNull(news.getPubtime())||isEmptyOrNull(news.getSource())){
			flag = false;
		}
		return flag;
	}

	//是否可以被索引
	public static boolean isRightIndex(News news){
		boolean flag = true;
		if(isEmptyOrNull(news.getBodytext())||isEmptyOrNull(news.getTitle())||isEmptyOrNull(news.getPubtime())||isEmptyOrNull(news.getSource())){
			flag = false;
		}
		return flag;
	}

	private static boolean isEmptyOrNull(String str){
		boolean flag = false;
		if(str==null||str.trim().isEmpty()){
			flag = true;
		}
		return flag;
	}

	//得到正确的时间格式
	public static String getRightTime(String sTime){
		if(sTime==null||sTime.trim().isEmpty()){
			return sTime;
		}
		String sRightTime = sTime.trim();
		if(sTime.contains("来源")){
			int iEndIndex = sTime.indexOf("来源");
			sRightTime = sTime.substring(0, iEndIndex);
		}
//		DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm"); 
//		sRightTime = format.format(sRightTime);
		return sRightTime.trim();
	}
}
