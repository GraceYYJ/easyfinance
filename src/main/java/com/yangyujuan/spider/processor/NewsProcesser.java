package com.yangyujuan.spider.processor;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import com.yangyujuan.jdbc.dao.NewsFilter;
import com.yangyujuan.jdbc.domain.News;
import com.yangyujuan.jdbc.domain.Processor;
import com.yangyujuan.spider.pipeline.NewsDaoPipeline;

public class NewsProcesser implements PageProcessor {

	private Processor processor ;
    private Site site ;
    
	public NewsProcesser(Processor processor){
		this.processor = processor;
		this.site = Site.me().setDomain(processor.getDomain());
	}
	
	public NewsProcesser(){}
	
    
    @Override
    public void process(Page page) {
    	 List<String> links = page.getHtml().links().regex(processor.getLinkstr()).all();
    	 List<String> helplinks = page.getHtml().links().regex(processor.getHelpurlstr()).all();
         page.addTargetRequests(links);
         page.addTargetRequests(helplinks);
         
         News news = new News();
         news.setBodytext(page.getHtml().xpath(processor.getBodytextstr()).toString());
         news.setPubTime(NewsFilter.getRightTime(page.getHtml().xpath(processor.getPubtimestr()).toString()));
         news.setSource(page.getHtml().xpath(processor.getSourcestr()).toString());
         news.setTitle(page.getHtml().xpath(processor.getTitlestr()).toString());
         if (!NewsFilter.isRightNews(news)) {
             page.setSkip(true);
         }
         page.putField("news",news);
    }

    @Override
    public Site getSite() {
        return site;
    }

}



