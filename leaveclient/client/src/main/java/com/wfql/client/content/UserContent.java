package com.wfql.client.content;

public class UserContent {
    public static final String AUTHORITIES = "com.wfql.server.provider.UserProvider";

    public static final String CONTENT_URI_TABLE = "content://" + AUTHORITIES + "/User/all";
    public static final String CONTENT_URI_SQL = "content://" + AUTHORITIES + "/User/sql";

}
