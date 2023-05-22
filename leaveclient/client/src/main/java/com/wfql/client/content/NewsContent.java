package com.wfql.client.content;

public class NewsContent {
    public static final String AUTHORITIES = "com.wfql.server.provider.NewsProvider";

    public static final String CONTENT_URI_TABLE = "content://" + AUTHORITIES + "/News/all";
    public static final String CONTENT_URI_SQL = "content://" + AUTHORITIES + "/News/sql";

}
