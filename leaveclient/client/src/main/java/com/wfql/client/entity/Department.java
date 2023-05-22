package com.wfql.client.entity;

public class Department {
    private String department;
    private String institution;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
                ", institution='" + institution + '\'' +
                '}';
    }
}
