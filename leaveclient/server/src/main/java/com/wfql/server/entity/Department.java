package com.wfql.server.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Institution.class,
                                parentColumns = "institution",
                                childColumns = "institution",
                                onDelete = ForeignKey.CASCADE,
                                onUpdate = ForeignKey.CASCADE))
public class Department {
    @PrimaryKey@NonNull
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
