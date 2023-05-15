package com.wfql.entity;

public class Login {
    private String loginid;
    private String loginpwd;
    private boolean isremember;

    public Login() {

    }

    public Login(String loginid, String loginpwd, boolean isremember) {
        this.loginid = loginid;
        this.loginpwd = loginpwd;
        this.isremember = isremember;
    }

    @Override
    public String toString() {
        return "Login{" +
                "loginid='" + loginid + '\'' +
                ", loginpwd='" + loginpwd + '\'' +
                ", isremember=" + isremember +
                '}';
    }

    public  boolean getIsremember() {
        return isremember;
    }

    public  String getLoginid() {
        return loginid;
    }

    public  String getLoginpwd() {
        return loginpwd;
    }

    public void setIsremember(boolean isremember) {
        this.isremember = isremember;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd;
    }
}
