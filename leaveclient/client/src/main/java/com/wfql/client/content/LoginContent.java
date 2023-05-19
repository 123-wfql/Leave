package com.wfql.client.content;

import android.net.Uri;

public class LoginContent {
    public static final String AUTHORITIES = "com.wfql.server.provider.LoginProvider";

    public static final String CONTENT_URI_TABLE = "content://" + AUTHORITIES + "/Login/all";
    public static final String CONTENT_URI_SQL = "content://" + AUTHORITIES + "/Login/sql";

}
