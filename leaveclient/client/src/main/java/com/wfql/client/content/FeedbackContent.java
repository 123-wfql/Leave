package com.wfql.client.content;

public class FeedbackContent {
    public static final String AUTHORITIES = "com.wfql.server.provider.FeedbackProvider";

    public static final String CONTENT_URI_TABLE = "content://" + AUTHORITIES + "/Feedback/all";
    public static final String CONTENT_URI_SQL = "content://" + AUTHORITIES + "/Feedback/sql";

}
