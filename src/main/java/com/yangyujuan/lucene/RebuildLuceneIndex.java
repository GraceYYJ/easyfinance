package com.yangyujuan.lucene;

import com.yangyujuan.jdbc.dao.NewsDao;
import com.yangyujuan.jdbc.dao.factory.NewsDaoFactory;
import com.yangyujuan.jdbc.domain.News;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by GraceYang on 2017/12/24.
 */
public class RebuildLuceneIndex {
    private NewsDao newsDao = NewsDaoFactory.getInstance().getDao();
    private static LuceneService luceneservice = new LuceneService();
    ArrayList<News> newsList = newsDao.getAllNewsList();
    public void rebuildLuceneIndex(){
        Iterator<News> iterator = newsList.iterator();
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
