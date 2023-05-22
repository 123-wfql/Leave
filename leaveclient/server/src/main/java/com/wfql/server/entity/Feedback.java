package com.wfql.server.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity/*(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "userId",
        childColumns = "fbAuthorId",
        onUpdate = ForeignKey.CASCADE))*/
public class Feedback {
    @PrimaryKey@NonNull
    private long feedbackId;
    private String fbContent;
    private long fbPeriod;
    private String fbAuthorId;

    public long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFbContent() {
        return fbContent;
    }

    public void setFbContent(String fbContent) {
        this.fbContent = fbContent;
    }

    public long getFbPeriod() {
        return fbPeriod;
    }

    public void setFbPeriod(long fbPeriod) {
        this.fbPeriod = fbPeriod;
    }

    public String getFbAuthorId() {
        return fbAuthorId;
    }

    public void setFbAuthorId(String fbAuthorId) {
        this.fbAuthorId = fbAuthorId;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", fbContent='" + fbContent + '\'' +
                ", fbPeriod=" + fbPeriod +
                ", fbAuthorId='" + fbAuthorId + '\'' +
                '}';
    }
}
