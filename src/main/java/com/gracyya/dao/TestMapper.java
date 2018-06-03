package com.gracyya.dao;

import com.gracyya.model.News;
import com.gracyya.model.Processor;
import com.gracyya.service.NewsService;
import com.gracyya.service.ProcessorService;
import com.gracyya.service.impl.NewsServiceImpl;
import com.gracyya.service.impl.ProcessorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class TestMapper {
    @Resource
    static ProcessorService processorService;
    @Resource
    static NewsService newsService;
    public static void main(String[] args) {
/*        News news=new News();
        news.setId(5L);
        news.setTitle("震惊！洋芋卷周末吃了两盒章鱼小丸子！");
        news.setPubtime("20180603");
        news.setSource("胖胖报社");
        news.setBodytext("章鱼小丸子是一种很好吃的丸子，一天10个都不会腻的，" +
                "但是洋芋卷太胖了，再吃下去自己也会变成五花肉小丸子了");
        news.setVisits(1l);
        long result=newsService.insertNews(news);
        System.out.println(result);*/

/*        Processor processor=new Processor();
        processor.setSitename("gracee.xin");
        processor.setDomain("gracee.xin");
        processor.setStarturl("Starturl");
        processor.setLinkstr("Linkstr");
        processor.setBodytextstr("章鱼小丸子是一种很好吃的丸子，一天10个都不会腻的，但是洋芋卷太胖了，再吃下去自己也会变成五花肉小丸子了");
        processor.setPubtimestr("20180603");
        processor.setSourcestr("胖胖报社");
        processor.setTitlestr("震惊！洋芋卷周末吃了两盒章鱼小丸子！");
        processor.setHelpurlstr("Helpurlstr");
        long result1=processorMapper.insert(processor);
        System.out.println(result1);*/

        Processor processor=new Processor();
        long result1=processorService.insert("A","A","A","A","A","A","A","A","A");
        System.out.println(result1);
    }

}
