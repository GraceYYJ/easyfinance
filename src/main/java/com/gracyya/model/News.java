package com.gracyya.model;

public class News {
    private Long id;

    private String title;

    private String source;

    private String pubtime;

    private Long visits;

    private String bodytext;

    public News(Long id, String title, String source, String pubtime, Long visits, String bodytext) {
        this.id = id;
        this.title = title;
        this.source = source;
        this.pubtime = pubtime;
        this.visits = visits;
        this.bodytext = bodytext;
    }

    public News() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getPubtime() {
        return pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime == null ? null : pubtime.trim();
    }

    public Long getVisits() {
        return visits;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }

    public String getBodytext() {
        return bodytext;
    }

    public void setBodytext(String bodytext) {
        this.bodytext = bodytext == null ? null : bodytext.trim();
    }
}