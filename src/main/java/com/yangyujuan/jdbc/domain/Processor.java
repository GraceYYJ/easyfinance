package com.yangyujuan.jdbc.domain;

public class Processor {

	private int id;
	private String sitename;
	private String domain;
	private String starturl;
	private String linkstr;
	private String bodytextstr;
	private String pubtimestr;
	private String sourcestr;
	private String titlestr;
	private String helpurlstr;
	
	public String getHelpurlstr() {
		return helpurlstr;
	}


	public void setHelpurlstr(String helpurlstr) {
		this.helpurlstr = helpurlstr;
	}


	public Processor(){}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSitename() {
		return sitename;
	}


	public void setSitename(String sitename) {
		this.sitename = sitename;
	}


	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}


	public String getStarturl() {
		return starturl;
	}


	public void setStarturl(String starturl) {
		this.starturl = starturl;
	}


	public String getLinkstr() {
		return linkstr;
	}


	public void setLinkstr(String linkstr) {
		this.linkstr = linkstr;
	}


	public String getBodytextstr() {
		return bodytextstr;
	}


	public void setBodytextstr(String bodytextstr) {
		this.bodytextstr = bodytextstr;
	}


	public String getPubtimestr() {
		return pubtimestr;
	}


	public void setPubtimestr(String pubtimestr) {
		this.pubtimestr = pubtimestr;
	}


	public String getSourcestr() {
		return sourcestr;
	}


	public void setSourcestr(String sourcestr) {
		this.sourcestr = sourcestr;
	}


	public String getTitlestr() {
		return titlestr;
	}


	public void setTitlestr(String titlestr) {
		this.titlestr = titlestr;
	}


	@Override
	public String toString() {
		return "id=" + this.id + " sitename=" + this.sitename  ;
	}
	
}
