package com.wfql.client.entity;

import android.content.ContentValues;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Login {
    @PrimaryKey@NonNull
    private String loginId;
    private String loginPwd;
    private boolean remember;

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("loginId", loginId);
        values.put("loginPwd", loginPwd);
        values.put("remember", remember);
        return values;
    }


    @Override
    public String toString() {
        return "Login{" +
                "loginId='" + loginId + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", remember=" + remember +
                '}';
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
