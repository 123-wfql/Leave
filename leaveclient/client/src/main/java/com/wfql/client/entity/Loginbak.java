package com.wfql.client.entity;

public class Loginbak {
    private String loginId;
    private String loginPwd;
    private boolean remember;

    public Loginbak() {

    }

    public Loginbak(String loginid, String loginpwd, boolean isremember) {
        this.loginId = loginid;
        this.loginPwd = loginpwd;
        this.remember = isremember;
    }

    @Override
    public String toString() {
        return "Login{" +
                "loginId='" + loginId + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", remember=" + remember +
                '}';
    }

    public  boolean isRemember() {
        return remember;
    }

    public  String getLoginId() {
        return loginId;
    }

    public  String getLoginPwd() {
        return loginPwd;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
}
