package com.wfql.client.content;

import android.net.Uri;

public class LoginContent {
    public static final String AUTHORITIES = "com.wfql.server.provider.LoginProvider";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITIES + "/login");
}
