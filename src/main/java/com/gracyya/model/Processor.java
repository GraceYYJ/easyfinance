package com.gracyya.model;

public class Processor {
    private Long id;

    private String sitename;

    private String domain;

    private String starturl;

    private String linkstr;

    private String bodytextstr;

    private String pubtimestr;

    private String sourcestr;

    private String titlestr;

    private String helpurlstr;

    public Processor(Long id, String sitename, String domain, String starturl, String linkstr, String bodytextstr, String pubtimestr, String sourcestr, String titlestr, String helpurlstr) {
        this.id = id;
        this.sitename = sitename;
        this.domain = domain;
        this.starturl = starturl;
        this.linkstr = linkstr;
        this.bodytextstr = bodytextstr;
        this.pubtimestr = pubtimestr;
        this.sourcestr = sourcestr;
        this.titlestr = titlestr;
        this.helpurlstr = helpurlstr;
    }

    public Processor() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename == null ? null : sitename.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getStarturl() {
        return starturl;
    }

    public void setStarturl(String starturl) {
        this.starturl = starturl == null ? null : starturl.trim();
    }

    public String getLinkstr() {
        return linkstr;
    }

    public void setLinkstr(String linkstr) {
        this.linkstr = linkstr == null ? null : linkstr.trim();
    }

    public String getBodytextstr() {
        return bodytextstr;
    }

    public void setBodytextstr(String bodytextstr) {
        this.bodytextstr = bodytextstr == null ? null : bodytextstr.trim();
    }

    public String getPubtimestr() {
        return pubtimestr;
    }

    public void setPubtimestr(String pubtimestr) {
        this.pubtimestr = pubtimestr == null ? null : pubtimestr.trim();
    }

    public String getSourcestr() {
        return sourcestr;
    }

    public void setSourcestr(String sourcestr) {
        this.sourcestr = sourcestr == null ? null : sourcestr.trim();
    }

    public String getTitlestr() {
        return titlestr;
    }

    public void setTitlestr(String titlestr) {
        this.titlestr = titlestr == null ? null : titlestr.trim();
    }

    public String getHelpurlstr() {
        return helpurlstr;
    }

    public void setHelpurlstr(String helpurlstr) {
        this.helpurlstr = helpurlstr == null ? null : helpurlstr.trim();
    }
}