package com.wfql.client.entity;

public class Institution {
    private String institution;
    private String insHeaderId;
    private String userType;

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getInsHeaderId() {
        return insHeaderId;
    }

    public void setInsHeaderId(String insHeaderId) {
        this.insHeaderId = insHeaderId;
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
                ", insHeaderId='" + insHeaderId + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
