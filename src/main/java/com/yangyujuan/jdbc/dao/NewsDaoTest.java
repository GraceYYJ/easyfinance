package com.yangyujuan.jdbc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yangyujuan.jdbc.dao.factory.NewsDaoFactory;
import com.yangyujuan.jdbc.dao.factory.ProcessorDaoFactory;
import com.yangyujuan.jdbc.dao.factory.UserDaoFactory;
import com.yangyujuan.jdbc.domain.News;
import com.yangyujuan.jdbc.domain.Processor;
import com.yangyujuan.jdbc.domain.User;
import com.yangyujuan.spider.processor.NewsProcesser;

public class NewsDaoTest {
	public static void main(String[] args) {
		NewsDao newsDao = NewsDaoFactory.getInstance().getDao();
		Pattern pattern = Pattern.compile("[^0-9]");
		
		int allPageCount = newsDao.getPageCount();
		for(int i = 0 ; i <= allPageCount ; i ++){
			ArrayList<News> list = newsDao.getNewsList(i);
			for(News news : list){
				System.out.println("##########################");
				System.out.println("old: "+news.getPubTime());
				String strOldTime = news.getPubTime();
		        Matcher matcher = pattern.matcher(strOldTime);
		        String all = matcher.replaceAll("");
		        String strNewTime =String.format("%s年%s月%s日 %s:%s",  all.substring(0, 4) , all.substring(4, 6), all.substring(6, 8), all.substring(8, 10), all.substring(10, 12)) ;
		        System.out.println("new: "+strNewTime);
		        
		        news.setPubTime(strNewTime);
		        newsDao.update(news);
			}
		}
		
//		int i = 0;
//		for(News news : list){
//			System.out.println("##########################"+ i++);
//			System.out.println(news.getPubTime());
//			
//		}
		//2015年05月02 08:30
		//哈哈,13888889999
		//2015-05-02 08:30:20
//		String phoneString = "2015-05-02 08:30:20";
//        // 提取数字
//        // 1
//        Pattern pattern = Pattern.compile("[^0-9]");
//        Matcher matcher = pattern.matcher(phoneString);
//        String all = matcher.replaceAll("");
//        String time =String.format("%s年%s月%s日 %s:%s",  all.substring(0, 4) , all.substring(4, 6), all.substring(6, 8), all.substring(8, 10), all.substring(10, 12)) ;
//        System.out.println("phone:" + all);
//        System.out.println("time:" + time);
//        // 2
//        String all2 = Pattern.compile("[^0-9]").matcher(phoneString).replaceAll("");
//        System.out.println("phone:" + all2);
		// UserDao userDao = new UserDaoJdbcImpl();
		// System.out.println(userDao);
		//		
//		News news = new News();
//		 news.setBodytext("bodytest杨");
//	        news.setPubTime("2015-07-12");
//	        news.setSource("yangding杨");
//	        news.setTitle("biaoti");
//	     news.setVisits(100);
//		 news.setBodytext("这些举措不可能");
//	     news.setPubTime("2015年08月29日 07:10");
//	     news.setSource("凤凰国际iMarkets");
//	     news.setTitle("这些举措不可能凭空发生2");
//		news.setPubTime(new java.sql.Date(new Date().getTime()));
		
//	     System.out.println(newsDao.addNews(news));
//		System.out.println(newsDao.getNews(1));
		

		// userDao.addUser(user);
		// System.out.println(user.getId());

//		User u = userDao.findUser(user.getName(), null);
//		System.out.println(u.getId());
//
//		u = userDao.getUser(1);
//		u.setMoney(20000.1f);
//		userDao.update(u);

		// User u1 = userDao.getUser(8);
		// userDao.delete(u1);

		// UserDaoImpl udi = new UserDaoImpl();
		// User u1 = udi.findUser(user.getName(), null);
		
//		UserDao dao = UserDaoFactory.getInstance().getUserDao();
// 		User user = dao.getByName("admin");
// 		String pas = dao.getPasswordByName("admin");
//		User user = dao.getById(1);
// 		System.out.println(pas);
		
//		ProcessorDao dao = ProcessorDaoFactory.getInstance().getDao();
//		Processor bean = new Processor();
//		bean = dao.getById(6);
//		System.out.println(bean);
//		bean.setDomain("finance.ifeng.com");
//		bean.setSitename("凤凰财经网");
//		bean.setLinkstr("http://finance\\.ifeng\\.com/a/\\d{8}/\\d+_\\d+\\.shtml");
//		bean.setPubtimestr("//span[@class='ss01']/text()");
//		bean.setSourcestr("//span[@class='ss03']/text()");
//		bean.setTitlestr("//title/text()");
//		bean.setBodytextstr("//div[@class='js_selection_area']");
//		bean.setStarturl("http://finance.ifeng.com/cmppdyn/756/665/1/dynlist.html");
		
//		bean.setDomain("finance.sina.com.cn");
//		bean.setSitename("新浪财经网");
//		bean.setLinkstr("http://roll\\.finance\\.sina\\.com\\.cn/finance/\\S{4}/\\S{4}/^[a-zA-Z0-9_]{1,}$.shtml");
//		bean.setPubtimestr("//span[@class='pubTime article-time']/text()");
//		bean.setSourcestr("//span[@class='where color-a-1']/a/text()");
//		bean.setTitlestr("//title/text()");
//		bean.setBodytextstr("//div[@id='Cnt-Main-Article-QQ']");
//		bean.setStarturl("http://roll.finance.sina.com.cn/finance/gjcj/gjjj/index.shtml");
		
//		bean.setDomain("finance.qq.com");
//		bean.setSitename("腾讯财经网");
//		bean.setLinkstr("/a/\\d{8}/\\d+\\.htm");
//		bean.setPubtimestr("//span[@class='pubTime article-time']/text()");
//		bean.setSourcestr("//span[@class='where color-a-1']/a/text()");
//		bean.setTitlestr("//title/text()");
//		bean.setBodytextstr("//div[@id='Cnt-Main-Article-QQ']");
//		bean.setStarturl("http://finance.qq.com/gdyw.htm");
		
		
//		bean.setDomain("money.163.com");
//		bean.setSitename("网易财经网");
//		bean.setLinkstr("http://money\\.163\\.com/15/\\d{4}/\\d{2}/\\S{16}\\.html");
//		bean.setPubtimestr("//div[@class='ep-time-soure cDGray']/text()");
//		bean.setSourcestr("//a[@id='ne_article_source']/text()");
//		bean.setTitlestr("//title/text()");
//		bean.setBodytextstr("//div[@id='endText']");
//		bean.setStarturl("http://money.163.com/special/00252G50/macro.html");
		
//		dao.add(bean);
		
//		NewsSpider spider = new NewsSpider();
//		spider.startSpider(bean);

	}
}



