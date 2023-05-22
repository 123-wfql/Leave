package com.wfql.client.entity;



public class User {
    private String userId;
    private String userPwd;
    private String userName;
    private String verifyPhone;
    private String userGender;
    private int enterYear;
    private String iconUrl;
    private int checkerLv;
    private String userRemark;
    private String department;
    private int governLv;
    private String job;

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userName='" + userName + '\'' +
                ", verifyPhone=" + verifyPhone +
                ", userGender='" + userGender + '\'' +
                ", enterYear=" + enterYear +
                ", iconUrl='" + iconUrl + '\'' +
                ", checkerLv=" + checkerLv +
                ", userRemark='" + userRemark + '\'' +
                ", department='" + department + '\'' +
                ", governLv=" + governLv +
                ", job='" + job + '\'' +
                '}';
    }

    public int getCheckerLv() {
        return checkerLv;
    }

    public void setCheckerLv(int checkerLv) {
        this.checkerLv = checkerLv;
    }

    public int getGovernLv() {
        return governLv;
    }

    public void setGovernLv(int governLv) {
        this.governLv = governLv;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVerifyPhone() {
        return verifyPhone;
    }

    public void setVerifyPhone(String verifyPhone) {
        this.verifyPhone = verifyPhone;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public int getEnterYear() {
        return enterYear;
    }

    public void setEnterYear(int enterYear) {
        this.enterYear = enterYear;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
