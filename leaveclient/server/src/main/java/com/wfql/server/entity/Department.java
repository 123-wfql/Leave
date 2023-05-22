package com.wfql.server.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Department {
    @PrimaryKey@NonNull
    private String department;
    private String institution;

    @NonNull
    public String getDepartment() {
        return department;
    }

    public void setDepartment(@NonNull String department) {
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
