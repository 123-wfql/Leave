package com.wfql.server.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LeaveType {
    @PrimaryKey@NonNull
    private String leaveType;
    private boolean needOut;

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public boolean isNeedOut() {
        return needOut;
    }

    public void setNeedOut(boolean needOut) {
        this.needOut = needOut;
    }

    @Override
    public String toString() {
        return "LeaveType{" +
                "leaveType='" + leaveType + '\'' +
                ", needOut=" + needOut +
                '}';
    }
}
