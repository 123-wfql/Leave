package com.wfql.server.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserType {
    @PrimaryKey@NonNull
    private String userType;
    private String utHeaderId;
    private String institutionTitle;
    private String departmentTitle;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUtHeaderId() {
        return utHeaderId;
    }

    public void setUtHeaderId(String utHeaderId) {
        this.utHeaderId = utHeaderId;
    }

    public String getInstitutionTitle() {
        return institutionTitle;
    }

    public void setInstitutionTitle(String institutionTitle) {
        this.institutionTitle = institutionTitle;
    }

    public String getDepartmentTitle() {
        return departmentTitle;
    }

    public void setDepartmentTitle(String departmentTitle) {
        this.departmentTitle = departmentTitle;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "userType='" + userType + '\'' +
                ", utHeaderId='" + utHeaderId + '\'' +
                ", institutionTitle='" + institutionTitle + '\'' +
                ", departmentTitle='" + departmentTitle + '\'' +
                '}';
    }
}
