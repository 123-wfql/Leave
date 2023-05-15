package com.wfql.client.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Admin {

    @PrimaryKey(autoGenerate = true)
    private int admId;

    private String admPwd;
    private String admName;
    private String admRemark;
    private String admGender;
    private String admContact;

    public int getAdmId() {
        return admId;
    }

    public void setAdmId(int admId) {
        this.admId = admId;
    }

    public String getAdmPwd() {
        return admPwd;
    }

    public void setAdmPwd(String admPwd) {
        this.admPwd = admPwd;
    }

    public String getAdmName() {
        return admName;
    }

    public void setAdmName(String admName) {
        this.admName = admName;
    }

    public String getAdmRemark() {
        return admRemark;
    }

    public void setAdmRemark(String admRemark) {
        this.admRemark = admRemark;
    }

    public String getAdmGender() {
        return admGender;
    }

    public void setAdmGender(String admGender) {
        this.admGender = admGender;
    }

    public String getAdmContact() {
        return admContact;
    }

    public void setAdmContact(String admContact) {
        this.admContact = admContact;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admId=" + admId +
                ", admPwd='" + admPwd + '\'' +
                ", admName='" + admName + '\'' +
                ", admRemark='" + admRemark + '\'' +
                ", admGender='" + admGender + '\'' +
                ", admContact='" + admContact + '\'' +
                '}';
    }


}
