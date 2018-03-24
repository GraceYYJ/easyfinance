package com.easy.finance.spider;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import com.easy.finance.jdbc.domain.Processor;
import com.easy.finance.spider.pipeline.NewsDaoPipeline;
import com.easy.finance.spider.processor.NewsProcesser;

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



