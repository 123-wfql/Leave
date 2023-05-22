package com.wfql.server.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity/*(foreignKeys = @ForeignKey(entity = UserType.class,
        parentColumns = "userType",
        childColumns = "userType",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE))*/
public class Institution {
    @PrimaryKey@NonNull
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
