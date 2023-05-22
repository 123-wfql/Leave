package com.wfql.client.content;

public class DepartmentContent {
    public static final String AUTHORITIES = "com.wfql.server.provider.DepartmentProvider";

    public static final String CONTENT_URI_TABLE = "content://" + AUTHORITIES + "/Department/all";
    public static final String CONTENT_URI_SQL = "content://" + AUTHORITIES + "/Department/sql";

}
