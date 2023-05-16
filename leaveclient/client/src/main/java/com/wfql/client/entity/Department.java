package com.wfql.client.entity;

public class Department {
    private String department;
    private String depHeaderId;
    private String institution;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepHeaderId() {
        return depHeaderId;
    }

    public void setDepHeaderId(String depHeaderId) {
        this.depHeaderId = depHeaderId;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    @Override
    public String toString() {
        return "Department{" +
                "department='" + department + '\'' +
                ", depHeaderId='" + depHeaderId + '\'' +
                ", institution='" + institution + '\'' +
                '}';
    }
}
