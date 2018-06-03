package com.gracyya.spider.processor;

import com.gracyya.model.News;
import com.gracyya.service.NewsFilter;
import com.gracyya.spider.pipeline.NewsDaoPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import java.util.List;

public class WangYiProcessor implements PageProcessor {
    private Site site = Site.me().setDomain("money.163.com");
    private NewsFilter newsFilter = new NewsFilter();
    @Override
    public void process(Page page) {
        List<String> links = page.getHtml().links().regex("http://money\\.163\\.com/\\d{2}/\\d{4}/\\d{2}/\\S{16}\\.html").all();
        page.addTargetRequests(links);
        News news = new News();
       // js_selection_area
        news.setBodytext(page.getHtml().xpath("//div[@id='endText']").toString());
        String pubTime = page.getHtml().xpath("//div[@class='post_time_source']/text()").toString();
        System.out.println(pubTime);
        if(pubTime != null){
            pubTime=pubTime.trim().substring(0, 19);
            System.out.println(pubTime);
            news.setPubtime(pubTime);
        }
       //news.setPubTime(page.getHtml().xpath("//div[@class='post_time_source']/text()").toString().trim().substring(0,19));
        news.setSource(page.getHtml().xpath("//a[@id='ne_article_source']/text()").toString());
        news.setTitle(page.getHtml().xpath("//title/text()").toString());
        System.out.println(news);
        if (!newsFilter.isRightNews(news)) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("news",news);
    }

    @Override
    public Site getSite() {
        return site;

    }

    public static void main(String[] args) {
//        Spider.create(new OschinaBlogPageProcesser()).addUrl("http://my.oschina.net/flashsword/blog")
//             .addPipeline(new ConsolePipeline()).run();
    	 Spider.create(new WangYiProcessor()).addUrl("http://money.163.com/special/00252G50/macro.html")
    	 .addPipeline(new NewsDaoPipeline()).setScheduler(new FileCacheQueueScheduler("D:\\project\\urllist")).run();
    }
}
