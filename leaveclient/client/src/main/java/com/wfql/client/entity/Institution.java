package com.wfql.client.entity;

public class Institution {
    private String institution;
    private String userType;

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "Institution{" +
                "institution='" + institution + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
