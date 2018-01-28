package com.yangyujuan.lucene;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import com.yangyujuan.jdbc.domain.News;
import com.yangyujuan.jdbc.domain.ViewNews;

public class Lucenetest {

	public static void main(String args[]) throws IOException, ParseException{
		LuceneService luceneservice = new LuceneService();
		luceneservice.getHotWordJson();
//		luceneservice.createIndex();
//		ArrayList<ViewNews> listnews;
//		try {
//			listnews = luceneservice.search("银行");
//			for(ViewNews model:listnews){
//				System.out.println(model.toString());
//			}
//		} catch (InvalidTokenOffsetsException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		luceneservice.getIndexMate();
//		luceneservice.getIDF();
	}
}
