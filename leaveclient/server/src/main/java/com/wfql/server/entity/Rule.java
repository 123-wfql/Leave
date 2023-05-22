package com.wfql.server.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity/*(foreignKeys = {
        @ForeignKey(entity = UserType.class,
                parentColumns = "userType",
                childColumns = "userType",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = LeaveType.class,
                parentColumns = "leaveType",
                childColumns = "leaveType",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE)
})*/
public class Rule {
    @PrimaryKey@NonNull
    private int ruleId;
    private String userType;
    private String leaveType;
    private boolean urgent;
    private boolean needCheck1;
    private boolean needCheck2;
    private boolean needCheck3;

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public boolean isNeedCheck1() {
        return needCheck1;
    }

    public void setNeedCheck1(boolean needCheck1) {
        this.needCheck1 = needCheck1;
    }

    public boolean isNeedCheck2() {
        return needCheck2;
    }

    public void setNeedCheck2(boolean needCheck2) {
        this.needCheck2 = needCheck2;
    }

    public boolean isNeedCheck3() {
        return needCheck3;
    }

    public void setNeedCheck3(boolean needCheck3) {
        this.needCheck3 = needCheck3;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "ruleId=" + ruleId +
                ", userType='" + userType + '\'' +
                ", leaveType='" + leaveType + '\'' +
                ", urgent=" + urgent +
                ", needCheck1=" + needCheck1 +
                ", needCheck2=" + needCheck2 +
                ", needCheck3=" + needCheck3 +
                '}';
    }
}
