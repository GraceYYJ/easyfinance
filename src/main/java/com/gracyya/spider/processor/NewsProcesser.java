package com.gracyya.spider.processor;

import com.gracyya.service.NewsFilter;
import com.gracyya.model.News;
import com.gracyya.model.Processor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

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
         news.setPubtime(NewsFilter.getRightTime(page.getHtml().xpath(processor.getPubtimestr()).toString()));
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



