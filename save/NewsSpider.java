package com.yangyujuan.spider;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import com.yangyujuan.jdbc.domain.Processor;
import com.yangyujuan.spider.pipeline.NewsDaoPipeline;
import com.yangyujuan.spider.processor.NewsProcesser;

public class NewsSpider {

	public NewsSpider(){}

    public void startSpider(Processor processor){
    	 Spider.create(new NewsProcesser(processor)).addUrl(processor.getStarturl())
    	 .addPipeline(new NewsDaoPipeline()).addPipeline(new ConsolePipeline()).run();
//    	Spider spider = Spider.create(new NewsProcesser(processor));
//    	spider.addUrl(processor.getStarturl()).addPipeline(new NewsDaoPipeline()).addPipeline(new ConsolePipeline()).start();
//    	spider.stop();
    }

}



