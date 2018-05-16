package com.gracyya.lucene;

import com.yangyujuan.jdbc.dao.NewsDao;
import com.yangyujuan.jdbc.dao.NewsFilter;
import com.yangyujuan.jdbc.dao.factory.NewsDaoFactory;
import com.yangyujuan.jdbc.domain.News;
import com.yangyujuan.jdbc.domain.ViewNews;
import com.yangyujuan.util.PropertiesUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.DocIdSetIterator;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.jsoup.Jsoup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LuceneService {

	private static String indexPath = PropertiesUtil.get("indexPath");
//	private static Directory directory = FSDirectory.open(Paths.get(indexPath));  
	private static Analyzer analyzer = new SimpleAnalyzer();

	/**
     * 获取高亮显示结果的html代码
     * @param query 查询
     * @param analyzer 分词器
     * @param fieldName 域名
     * @param fieldContent 域内容
     * @param fragmentSize 结果的长度（不含html标签长度）
     * @return 结果（一段html代码）
     * @throws IOException
     * @throws InvalidTokenOffsetsException
     */
    static String displayHtmlHighlight(Query query, Analyzer analyzer, String fieldName, String fieldContent, int fragmentSize) throws IOException, InvalidTokenOffsetsException
    {
        //创建一个高亮器
        Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter("<font color='red'>", "</font>"), new QueryScorer(query));
        Fragmenter fragmenter = new SimpleFragmenter(fragmentSize);
        highlighter.setTextFragmenter(fragmenter);
        return highlighter.getBestFragment(analyzer, fieldName, fieldContent);
    }

	public void createIndex() throws IOException {
		 IndexWriter writer = null;
	        try {
//	        	String indexPath = PropertiesUtil.get("indexPath");
	            // Directory directory = new RAMDirectory();
	            Directory directory = FSDirectory.open(Paths.get(indexPath));
//	            Analyzer analyzer = new StandardAnalyzer();
//	            Analyzer analyzer = new MMAnalyzer();//中文分词器
//	            Analyzer analyzer = new org.apache.lucene.analysis.core.SimpleAnalyzer();
//	            Analyzer analyzer =  new CJKAnalyzer();
	            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
	            writer = new IndexWriter(directory, iwc);
	            Document document = null;

	            NewsDao newsDao = NewsDaoFactory.getInstance().getDao();
	    		ArrayList<News> list = newsDao.getAllNewsList();
	    		FieldType type = new FieldType();
	    		type.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
	    		type.setTokenized(true);
	    		type.setStored(true);
	    		type.setStoreTermVectors(true);

	    		for(News news : list){
	    			if(!NewsFilter.isRightIndex(news)){
	    				continue;
	    			}
//	    			System.out.println("title:" + news.getTitle());
	                document = new Document();
//	                Field field = new Field("body", Jsoup.parse(news.getBodytext()).text(), type);
//	                document.add(new IntField("id", news.getId(), Field.Store.YES));
//	                document.add(new StringField("title", news.getTitle(), Field.Store.YES));
//	                document.add(new StringField("source", news.getSource(), Field.Store.YES));
//	                document.add(new StringField("path", news.getPubTime(), Field.Store.YES));
	                //由于正文是带html标签的字符串，所有用Jsoup去掉标签
//	                document.add(new TextField("bodytext", Jsoup.parse(news.getBodytext()).text(), Field.Store.YES));
//	                document.add(new TextField("bodytexthtml", news.getBodytext(), Field.Store.NO));
	                document.add(new Field("id", String.valueOf(news.getId()), type));
	                document.add(new Field("title", news.getTitle(), type));
	                document.add(new Field("source", news.getSource(), type));
	                document.add(new Field("path", news.getPubTime(), type));
	                document.add(new Field("bodytext", Jsoup.parse(news.getBodytext()).text(), type));

	                writer.addDocument(document);
	    		}
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } finally {
	            if (writer != null) {
	                try {
	                    writer.close();
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	        }
	}


	public void addIndex(News news){
		 IndexWriter writer = null;
	        try {
	            Directory directory = FSDirectory.open(Paths.get(indexPath));
	            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
	            writer = new IndexWriter(directory, iwc);
	            Document document = null;
	    		FieldType type = new FieldType();
	    		type.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
	    		type.setTokenized(true);
	    		type.setStored(true);
	    		type.setStoreTermVectors(true);
	    		FieldType type2 = new FieldType();
	    		type2.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
	    		type2.setTokenized(false);
	    		type2.setStored(true);
	    		type2.setStoreTermVectors(false);
	    		if(NewsFilter.isRightIndex(news)){
//    				System.out.println("title:" + news.getTitle());  
	                document = new Document();  
	                document.add(new Field("id", String.valueOf(news.getId()), type2));
	                document.add(new Field("title", news.getTitle(), type));
	                document.add(new Field("source", news.getSource(), type));  
	                document.add(new Field("path", news.getPubTime(), type));
	                document.add(new Field("bodytext", Jsoup.parse(news.getBodytext()).text(), type));
	                writer.addDocument(document);  
    			}
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (writer != null) {  
	                try {  
	                    writer.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        } 
	}
	
	
	public ArrayList<ViewNews> search(String keyword) throws ParseException, InvalidTokenOffsetsException{
	    ArrayList<ViewNews> ls = new ArrayList<ViewNews>();
	    
//	    String indexPath = PropertiesUtil.get("indexPath");
        // Directory directory = new RAMDirectory();  
         if(keyword.equalsIgnoreCase("")||keyword==null||keyword.isEmpty()){
        	 return ls;
         }
		try {
			Directory directory = FSDirectory.open(Paths.get(indexPath));
//			Analyzer analyzer = new StandardAnalyzer();  
//			Analyzer analyzer = new MMAnalyzer();//中文分词器
//			Analyzer analyzer = new SimpleAnalyzer();
			DirectoryReader ireader = DirectoryReader.open(directory);
			
			IndexSearcher isearcher = new IndexSearcher(ireader);
			QueryParser parser = new QueryParser("bodytext", analyzer);
			Query query = parser.parse(keyword);
			ScoreDoc[] hits = isearcher.search(query, 100).scoreDocs;
			for (int i = 0; i < hits.length; i++) {
//				System.out.println(hits[i].score);
				Document hitDoc = isearcher.doc(hits[i].doc);
//				String title = displayHtmlHighlight(query, analyzer, "title", hitDoc.get("title"), 200);
				ls.add(document2News(hitDoc,keyword,hits[i].score,hits[0].score));
			}
			ireader.close();
			directory.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return ls;
	}
	
	public ViewNews document2News(Document document,String keyword,float score,float maxScore){
		ViewNews  model = new ViewNews();
		model.setId(Integer.parseInt(document.get("id")));
		model.setTitle(document.get("title").replaceAll(keyword, "<font color='red'>"+keyword+"</font>"));
		model.setSource(document.get("source"));
		model.setPubTime(document.get("path"));
		model.setBodytext(document.get("bodytext"));
		DecimalFormat decimalFormat=new DecimalFormat(".00");
		String p= decimalFormat.format((score*100/maxScore));
		model.setScore(Float.valueOf(p));
		return model;
	}
	
	
	public void getIndexMate(){
		 try {
			 	JSONObject jsonall = new JSONObject();
			 	ArrayList<HashMap> jsonlist = new ArrayList<HashMap>();
//			 	String indexPath = PropertiesUtil.get("indexPath");
	            // Directory directory = new RAMDirectory();  
	            Directory directory = FSDirectory.open(Paths.get(indexPath));  
//	            Directory directroy = FSDirectory.open(new File(
//	                    INDEX_PATH));
	            IndexReader reader = DirectoryReader.open(directory);
	            //reader.numDocs()
	            for (int i = 0; i <5; i++) {
	                int docId = i;
	                
	                HashMap<String,Object> hashmap = new HashMap<String,Object>();
                	hashmap.put("name", i);
                	ArrayList<HashMap> termlist = new ArrayList<HashMap>();
                	
                	
	                System.out.println("第" + (i + 1) + "篇文档：");
	                Terms terms = reader.getTermVector(docId, "bodytext");
	                if (terms == null){
	                    continue;
	                }else{
	                	System.out.println(terms.toString());
	                }
	                TermsEnum termsEnum = terms.iterator();
	                BytesRef thisTerm = null;
	                while ((thisTerm = termsEnum.next()) != null) {
	                    String termText = thisTerm.utf8ToString();
	                    DocsEnum docsEnum = termsEnum.docs(null, null);
	                    while ((docsEnum.nextDoc()) != DocIdSetIterator.NO_MORE_DOCS) {
//	                        System.out.println("termText:" + termText + " TF:  " + 1.0 * docsEnum.freq() / terms.size());
	                    	System.out.println("termText:" + termText + " 频数:  " + 1.0 * docsEnum.freq());
	                    	
	                    	HashMap<String,Object> term = new HashMap<String,Object>();
	                    	term.put("name", termText);
	                    	term.put("size", 1.0 * docsEnum.freq());
	                    	termlist.add(term);
	                    	
	                    	//{"name": "flare","children": [ {"name": "analytics","children": [      ]}]}
	                    }
	                }
	                JSONArray json = JSONArray.fromObject(termlist);
                	hashmap.put("children", json);
                	
	                jsonlist.add(hashmap);
//	                jsonall.putAll(hashmap);
	            }
	            JSONArray jsonarry = JSONArray.fromObject(jsonlist);
//	            jsonall.putAll(hashmap);
	            writeJson2File("e:/filename.json",jsonarry);
	            
	            reader.close();
	            directory.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	
	/*
     * 计算IDF
     * 
     * *
     */
    public void getIDF() {
        try {
        	String indexPath = PropertiesUtil.get("indexPath");
            // Directory directory = new RAMDirectory();  
            Directory directory = FSDirectory.open(Paths.get(indexPath));  
//            Directory directory = FSDirectory.open(new File(INDEX_PATH));
            IndexReader reader = DirectoryReader.open(directory);
//            List<AtomicReaderContext> list = reader.leaves();
            List<LeafReaderContext> list = reader.leaves();
            System.out.println("文档总数 : " + reader.maxDoc());
            System.out.println("list : " + list.size());
            ArrayList<HashMap> jsonlist = new ArrayList<HashMap>();
            HashMap<String,Object> hashmap = new HashMap<String,Object>();
        	hashmap.put("name", 1);
        	ArrayList<HashMap> termlist = new ArrayList<HashMap>();
        	
            int count = 0;
            for (LeafReaderContext ar : list) {
                String field = "bodytext";
//                AtomicReader areader = ar.reader();
                LeafReader areader = ar.reader();
                Terms terms = areader.terms(field);
                TermsEnum tn = terms.iterator();
                BytesRef text;
                while ((text = tn.next()) != null) {
                	double idf = Math.log10(reader.maxDoc() * 1.0 / tn.docFreq());
                	if(tn.totalTermFreq()>9 && idf>3.0000&&(!text.utf8ToString().matches("^[a-zA-Z]*"))&&text.utf8ToString().length()>1){
                		HashMap<String,Object> term = new HashMap<String,Object>();
                    	term.put("name", text.utf8ToString());
                    	term.put("size", idf*tn.totalTermFreq());
                    	termlist.add(term);
                    	count++;
                    }
                }
            }
            JSONArray json = JSONArray.fromObject(termlist);
        	hashmap.put("children", json );
            jsonlist.add(hashmap);
            JSONArray jsonarry = JSONArray.fromObject(jsonlist);
            String filepath = "D:/programming/WorkplaceEclipseEE/YangyujuanV1/src/main/webapp/toutiao/flare5.json";
            //"e:/filename2.json"
            writeJson2File(filepath,jsonarry);
            
            System.out.println("count : " + count);
            reader.close();
            directory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void writeJson2File(String filepath,JSONArray jsonarry){
    	try {
    	File file = new File(filepath);
        // if file doesnt exists, then create it
        if (!file.exists()) {
			file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("{\"name\": \"flare\",\"children\": [ {\"name\": \"analytics\",\"children\": " + jsonarry + "}]}");
        bw.close();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //得到热词数据的json
    public String getHotWordJson() {
    	String hotWord ="";
        try {
        	String indexPath = PropertiesUtil.get("indexPath");
            Directory directory = FSDirectory.open(Paths.get(indexPath));  
            IndexReader reader = DirectoryReader.open(directory);
            List<LeafReaderContext> list = reader.leaves();
            ArrayList<HashMap> jsonlist = new ArrayList<HashMap>();
            HashMap<String,Object> hashmap = new HashMap<String,Object>();
        	hashmap.put("name", 1);
        	ArrayList<HashMap> termlist = new ArrayList<HashMap>();
            int count = 0;
            for (LeafReaderContext ar : list) {
                String field = "bodytext";
                LeafReader areader = ar.reader();
                Terms terms = areader.terms(field);
                TermsEnum tn = terms.iterator();
                BytesRef text;
                while ((text = tn.next()) != null) {
                	double idf = Math.log10(reader.maxDoc() * 1.0 / tn.docFreq());
                	//9    3.0000   
//                	if(tn.totalTermFreq()>9 && tn.totalTermFreq() < 50 && idf>3.3000 && idf < 4.0000 &&(!text.utf8ToString().matches("^[a-zA-Z]*"))&&text.utf8ToString().length()>1){
//                		HashMap<String,Object> term = new HashMap<String,Object>();
//                		System.out.println(text.utf8ToString() + " ： idf= " + idf  + "  ;fre =" + tn.totalTermFreq());
//                    	term.put("name", text.utf8ToString());
//                    	term.put("size", idf*Math.sqrt(tn.totalTermFreq()));
//                    	termlist.add(term);
//                		count++;
//                    }
                	if(tn.totalTermFreq()>10&& idf>1.8000 && idf < 4.0000 &&(!text.utf8ToString().matches("^[a-zA-Z]*"))&&text.utf8ToString().length()>1){
                		HashMap<String,Object> term = new HashMap<String,Object>();
                		System.out.println(text.utf8ToString() + " ： idf= " + idf  + "  ;fre =" + tn.totalTermFreq());
                    	term.put("name", text.utf8ToString());
                    	term.put("size", idf*Math.sqrt(tn.totalTermFreq()));
                    	termlist.add(term);
                		count++;
                    }
                }
            }
            JSONArray json = JSONArray.fromObject(termlist);
        	hashmap.put("children", json);
            jsonlist.add(hashmap);
            JSONArray jsonarry = JSONArray.fromObject(jsonlist);
            hotWord =  "{\"name\": \"flare\",\"children\": [ {\"name\": \"analytics\",\"children\": " + jsonarry + "}]}";
            reader.close();
            directory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return hotWord;
    }
    
}
