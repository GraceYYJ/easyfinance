package com.gracyya.spider.pipeline;

import com.gracyya.lucene.LuceneService;
import com.gracyya.service.NewsService;
import com.gracyya.model.News;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NewsDaoPipeline implements Pipeline{

	private static LuceneService luceneservice = new LuceneService();
	@Resource
	NewsService newsService;
	private static Pattern pattern = Pattern.compile("[^0-9]");
	@Override
	public void process(ResultItems resultItems, Task task) {
		News news = (News)resultItems.get("news");
		System.out.println("news: "+ news);
		String strNewTime = news.getPubtime();
		Matcher matcher = pattern.matcher(news.getPubtime());
        String all = matcher.replaceAll("");
        if(all.length()>=12){
        	strNewTime=String.format("%s年%s月%s日 %s:%s",  all.substring(0, 4) , all.substring(4, 6), all.substring(6, 8), all.substring(8, 10), all.substring(10, 12)) ;
        }else if(all.length()>=8){
        	strNewTime=String.format("%s年%s月%s日 %s:%s",  all.substring(0, 4) , all.substring(4, 6), all.substring(6, 8), "00", "00") ;
        }
        news.setPubtime(strNewTime);
        news.setVisits(1l);
		//在这里发布消息，数据库服务订阅并addnews，如果消费失败则清除消息
		Long iResultId = newsService.insertNews(news);
		if(iResultId>0){
		    System.out.println("插入成功"+iResultId);
			news.setId(iResultId);
			luceneservice.addIndex(news);
		}
	}
/*	public void process2(ResultItems resultItems,Task task){
		News news = (News)resultItems.get("news");
		String strNewTime = news.getPubtime();
		Matcher matcher = pattern.matcher(news.getPubtime());
		String all = matcher.replaceAll("");
		if(all.length()>=12){
			strNewTime=String.format("%s年%s月%s日 %s:%s",  all.substring(0, 4) , all.substring(4, 6), all.substring(6, 8), all.substring(8, 10), all.substring(10, 12)) ;
		}else if(all.length()>=8){
			strNewTime=String.format("%s年%s月%s日 %s:%s",  all.substring(0, 4) , all.substring(4, 6), all.substring(6, 8), "00", "00") ;
		}
	}*/
}
