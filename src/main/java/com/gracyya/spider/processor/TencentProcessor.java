package com.gracyya.spider.processor;

import com.gracyya.model.News;
import com.gracyya.service.NewsFilter;
import com.gracyya.spider.pipeline.NewsDaoPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class TencentProcessor implements PageProcessor{
    private Site site = Site.me().setDomain("finance.qq.com");
    
    private NewsFilter newsFilter = new NewsFilter();
    public void process(Page page) {
    	//http://finance.ifeng.com/a/20151128/14096623_0.shtml
    	///a/20150601/010592.htm
        //List<String> links = page.getHtml().links().regex("http://finance\\.ifeng\\.com/a/\\d{8}/\\d+_\\d+\\.shtml").all();
    	List<String> links = page.getHtml().links().regex("/a/\\d{8}/\\d+\\.htm").all();
    	page.addTargetRequests(links);
        News news = new News();
       // js_selection_area
        news.setBodytext(page.getHtml().xpath("//div[@id='Cnt-Main-Article-QQ']").toString());
        news.setPubtime(page.getHtml().xpath("//span[@class='pubTime article-time']/text()").toString());
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
//        Spider.create(new OschinaBlogPageProcesser()).addUrl("http://my.oschina.net/flashsword/blog")
//             .addPipeline(new ConsolePipeline()).run();
    	 Spider.create(new TencentProcessor()).addUrl("http://finance.qq.com/gdyw.htm")
    	 .addPipeline(new NewsDaoPipeline()).run();
    }
}
