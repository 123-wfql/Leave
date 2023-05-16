package com.wfql.client.entity;

public class News {
    private long newsId;
    private String newsContent;
    private long newsPeriod;
    private String newsAuthorId;

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public long getNewsPeriod() {
        return newsPeriod;
    }

    public void setNewsPeriod(long newsPeriod) {
        this.newsPeriod = newsPeriod;
    }

    public String getNewsAuthorId() {
        return newsAuthorId;
    }

    public void setNewsAuthorId(String newsAuthorId) {
        this.newsAuthorId = newsAuthorId;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", newsContent='" + newsContent + '\'' +
                ", newsPeriod=" + newsPeriod +
                ", newsAuthorId='" + newsAuthorId + '\'' +
                '}';
    }
}
