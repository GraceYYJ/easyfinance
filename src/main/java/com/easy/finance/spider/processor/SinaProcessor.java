package com.easy.finance.spider.processor;

import java.util.List;

import com.easy.finance.jdbc.dao.NewsFilter;
import com.easy.finance.jdbc.domain.News;
import com.easy.finance.spider.pipeline.NewsDaoPipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class SinaProcessor implements PageProcessor{
    private Site site = Site.me().setDomain("finance.sina.com.cn");
    
    private NewsFilter newsFilter = new NewsFilter();
    public void process(Page page) {
    	//http://roll.finance.sina.com.cn/finance/gjcj/qtdq/index.shtml
    	List<String> links = page.getHtml().links().regex("http://roll\\.finance\\.sina\\.com\\.cn/finance/\\S{4}/\\S{4}/^[a-zA-Z0-9_]{1,}$.shtml").all();
    	page.addTargetRequests(links);
        News news = new News();
       
        news.setBodytext(page.getHtml().xpath("//div[@id='Cnt-Main-Article-QQ']").toString());
        news.setPubTime(page.getHtml().xpath("//span[@class='pubTime article-time']/text()").toString());
        news.setSource(page.getHtml().xpath("//span[@class='where color-a-1']/a/text()").toString());
        news.setTitle(page.getHtml().xpath("//title/text()").toString());
        if (!newsFilter.isRightNews(news)) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("news",news);
//        page.putField("title", page.getHtml().xpath("//div[@class='BlogEntity']/div[@class='BlogTitle']/h1").toString());
//        page.putField("content", page.getHtml().$("div.content").toString());
//        page.putField("tags",page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());
    }

    
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
    	//http://roll.finance.sina.com.cn/finance/gjcj/gjjj/index.shtml
    	//http://finance.sina.com.cn/world/
    	 Spider.create(new SinaProcessor()).addUrl("http://roll.finance.sina.com.cn/finance/gjcj/gjjj/index.shtml")
    	 .addPipeline(new NewsDaoPipeline()).run();
    }
}
