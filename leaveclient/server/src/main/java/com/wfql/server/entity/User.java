package com.wfql.server.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey@NonNull
    private String userId;
    private String userPwd;
    private String userName;
    private long verifyPhone;
    private String userGender;
    private int enterYear;
    private String iconUrl;
    private boolean checker;
    private String userRemark;
    private String depName;

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

    public long getVerifyPhone() {
        return verifyPhone;
    }

    public void setVerifyPhone(long verifyPhone) {
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

    public boolean isChecker() {
        return checker;
    }

    public void setChecker(boolean checker) {
        this.checker = checker;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

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
                ", checker=" + checker +
                ", userRemark='" + userRemark + '\'' +
                ", depName='" + depName + '\'' +
                '}';
    }
}
