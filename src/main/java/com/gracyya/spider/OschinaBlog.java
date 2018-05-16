package com.gracyya.spider;

//import java.awt.List;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.List;

@TargetUrl("http://my.oschina.net/flashsword/blog/\\d+")
public class OschinaBlog {

    @ExtractBy("//title")
    private String title;

    @ExtractBy(value = "div.BlogContent",type = ExtractBy.Type.Css)
    private String content;

    @ExtractBy(value = "//div[@class='BlogTags']/a/text()", multi = true)
    private List<String> tags;

    @Override
    public String toString(){
    	return title+content+tags;
    }
    
    public static void main(String[] args) {
        OOSpider.create(
            Site.me(),
            new ConsolePageModelPipeline(), OschinaBlog.class).addUrl("http://my.oschina.net/flashsword/blog").run();
       
    }
}
