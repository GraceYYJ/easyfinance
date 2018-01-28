package com.yangyujuan.spider.processor;
import org.apache.commons.collections.CollectionUtils;

import com.yangyujuan.jdbc.dao.NewsFilter;
import com.yangyujuan.jdbc.domain.News;
import com.yangyujuan.spider.pipeline.NewsDaoPipeline;
import com.yangyujuan.spider.pipeline.NewsDaoTestPipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.List;

/**
 * @author code4crafter@gmail.com
 * @since 0.5.0
 */
public class SinaJsonProcessor implements PageProcessor {

    private Site site = Site.me();

    private static final String ARITICALE_URL = "http://angularjs\\.cn/api/article/\\w+";

    //http://feed.mix.sina.com.cn/api/roll/get?pageid=155&lid=1686&num=50&page=1
//    private static final String LIST_URL = "http://angularjs\\.cn/api/article/latest.*";
    private static final String LIST_URL = "http://feed\\.mix\\.sina\\.com\\.cn/api/roll/get\\?pageid\\=155\\&lid\\=1686\\&num\\=50\\&page\\=.*";

    @Override
    public void process(Page page) {
    	System.out.println("page.getUrl() = " + page.getUrl());
//    	System.out.println("page.getRawText() = " + page.getRawText());
        if (page.getUrl().regex(LIST_URL).match()) {
            List<String> urls = new JsonPathSelector("$.result.data[*].url").selectList(page.getRawText());
            if (CollectionUtils.isNotEmpty(urls)) {
                for (String url : urls) {
                    page.addTargetRequest(url);
                }
            }
        } else {
        	News news = new News();
             news.setBodytext(page.getHtml().xpath("//div[@id='artibody']").toString());
             news.setPubTime(page.getHtml().xpath("//span[@class='time-source']/text()").toString());
             news.setSource(page.getHtml().xpath("//span[@data-sudaclick='media_name']/a/text()").toString());
             news.setTitle(page.getHtml().xpath("//h1[@id='artibodyTitle']/text()").toString());
             if (!NewsFilter.isRightNews(news)) {
                 page.setSkip(true);
             }
             page.putField("news",news);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new SinaJsonProcessor()).addUrl("http://feed.mix.sina.com.cn/api/roll/get?pageid=155&lid=1686&num=50&page=3")
        .addPipeline(new NewsDaoPipeline()).addPipeline(new ConsolePipeline()).run();
    }
}