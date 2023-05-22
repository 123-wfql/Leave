package com.wfql.client.entity;

public class UserType {
    private String userType;
    private String institutionTitle;
    private String departmentTitle;
    private String idTag;

    @Override
    public String toString() {
        return "UserType{" +
                "userType='" + userType + '\'' +
                ", institutionTitle='" + institutionTitle + '\'' +
                ", departmentTitle='" + departmentTitle + '\'' +
                ", idTag='" + idTag + '\'' +
                '}';
    }

    public String getIdTag() {
        return idTag;
    }

    public void setIdTag(String idTag) {
        this.idTag = idTag;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

}
