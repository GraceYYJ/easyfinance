package com.gracyya.spider.pipeline;

import com.gracyya.model.News;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class NewsDaoTestPipeline implements Pipeline{
	//private NewsDao newsDao = DaoFactory.getInstance().getUserDao();
	@Override
	public void process(ResultItems resultItems, Task task) {
		News news = (News)resultItems.get("news");
		System.out.println("news: "+ news);
		//newsDao.addNews(news);
	}
}
