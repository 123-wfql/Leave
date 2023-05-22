package com.wfql.server.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity/*(foreignKeys = @ForeignKey(entity = Admin.class,
        parentColumns = "adminId",
        childColumns = "newsAuthorId",
        onUpdate = ForeignKey.CASCADE))*/
public class News {
    @PrimaryKey@NonNull
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
