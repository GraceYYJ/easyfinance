package com.gracyya.spider;

import com.gracyya.spider.pipeline.NewsDaoPipeline;
import com.gracyya.spider.processor.NewsProcesser;
import com.yangyujuan.jdbc.domain.Processor;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

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



