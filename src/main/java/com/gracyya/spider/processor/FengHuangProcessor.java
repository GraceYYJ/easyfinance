package com.gracyya.spider.processor;

import com.gracyya.model.News;
import com.gracyya.service.NewsFilter;
import com.gracyya.spider.pipeline.NewsDaoPipeline;
import com.gracyya.spider.pipeline.NewsDaoTestPipeline;
import com.gracyya.util.BeanUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.component.BloomFilterDuplicateRemover;

import java.util.List;

public class FengHuangProcessor implements PageProcessor {
    private Site site = Site.me().setDomain("finance.ifeng.com");
    private NewsFilter newsFilter = new NewsFilter();
    @Override
    public void process(Page page) {
    	//http://finance.ifeng.com/a/20151128/14096623_0.shtml
        List<String> links = page.getHtml().links().regex("http://finance\\.ifeng\\.com/a/\\d{8}/\\d+_\\d+\\.shtml").all();
        page.addTargetRequests(links);
        News news = new News();
       // js_selection_area
        news.setBodytext(page.getHtml().xpath("//div[@class='js_selection_area']").toString());
        news.setPubtime(page.getHtml().xpath("//span[@class='ss01']/text()").toString());
        news.setSource(page.getHtml().xpath("//span[@class='ss03']/text()").toString());
        news.setTitle(page.getHtml().xpath("//title/text()").toString());
/*        if (!newsFilter.isRightNews(news)) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("news",news);*/
        if(newsFilter.isRightNews(news)){
            page.putField("news",news);
        }
        else {
            page.setSkip(true);
        }
//        page.putField("title", page.getHtml().xpath("//div[@class='BlogEntity']/div[@class='BlogTitle']/h1").toString());
//        page.putField("content", page.getHtml().$("div.content").toString());
//        page.putField("tags",page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());
    }

    @Override
    public Site getSite() {
        return site;

    }

    public static void main(String[] args) {
//        Spider.create(new OschinaBlogPageProcesser()).addUrl("http://my.oschina.net/flashsword/blog")
//             .addPipeline(new ConsolePipeline()).run();
    	//http://finance.ifeng.com/
    	//http://finance.ifeng.com/cmppdyn/756/665/1/dynlist.html
        NewsDaoPipeline newsDaoPipeline = BeanUtil.getBean(NewsDaoPipeline.class);
        Spider.create(new FengHuangProcessor()).
                addUrl("http://finance.ifeng.com").
                scheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000))).
                addPipeline(newsDaoPipeline).run();
    }
}
