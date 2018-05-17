package com.gracyya.spider.pipeline;

import com.gracyya.lucene.LuceneService;
import com.yangyujuan.jdbc.dao.NewsDao;
import com.yangyujuan.jdbc.dao.factory.NewsDaoFactory;
import com.yangyujuan.jdbc.domain.News;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsDaoPipeline implements Pipeline{

	private NewsDao newsDao = NewsDaoFactory.getInstance().getDao();
	private static LuceneService luceneservice = new LuceneService();
	private static Pattern pattern = Pattern.compile("[^0-9]");
	@Override
	public void process(ResultItems resultItems, Task task) {
		News news = (News)resultItems.get("news");
		System.out.println("news: "+ news);
		String strNewTime = news.getPubTime();
		Matcher matcher = pattern.matcher(news.getPubTime());
        String all = matcher.replaceAll("");
        if(all.length()>=12){
        	strNewTime=String.format("%s年%s月%s日 %s:%s",  all.substring(0, 4) , all.substring(4, 6), all.substring(6, 8), all.substring(8, 10), all.substring(10, 12)) ;
        }else if(all.length()>=8){
        	strNewTime=String.format("%s年%s月%s日 %s:%s",  all.substring(0, 4) , all.substring(4, 6), all.substring(6, 8), "00", "00") ;
        }
        news.setPubTime(strNewTime);
		//在这里发布消息，数据库服务订阅并addnews，如果消费失败则清除消息
		int iResultId = newsDao.addNews(news);
		if(iResultId>0){
			news.setId(iResultId);
			luceneservice.addIndex(news);
		}
	}
}
