package com.gracyya.lucene;

import com.gracyya.service.NewsService;
import com.gracyya.model.News;

import java.util.Iterator;
import java.util.List;

/**
 * Created by GraceYang on 2017/12/24.
 */
public class RebuildLuceneIndex {
    NewsService newsService;
    List<News> newsArrayList=newsService.getAllNews();
    private static LuceneService luceneservice = new LuceneService();
    public void rebuildLuceneIndex(){
        Iterator<News> iterator = newsArrayList.iterator();
        while(iterator.hasNext()){
            News news=iterator.next();
            System.out.println("news: "+ news);
            luceneservice.addIndex(news);
        }
    }
    public static void main(String[] args) {
        RebuildLuceneIndex test=new RebuildLuceneIndex();
        test.rebuildLuceneIndex();
    }

}
