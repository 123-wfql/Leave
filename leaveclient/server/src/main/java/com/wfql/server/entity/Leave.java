package com.wfql.server.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "userId",
                childColumns = "userId",
                onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = User.class,
                parentColumns = "userId",
                childColumns = "check1",
                onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = User.class,
                parentColumns = "userId",
                childColumns = "check2",
                onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = User.class,
                parentColumns = "userId",
                childColumns = "check3",
                onUpdate = ForeignKey.CASCADE)
        })
public class Leave {
    @PrimaryKey@NonNull
    private long leaveId;
    private String userId;
    private long periodStart;
    private long periodEnd;
    private long periodSubmit;
    private String leaveType;
    private String leaveReason;
    private String destination;
    private int status;
    private boolean finish;
    private boolean urgent;
    private String check1;
    private String check1Status;
    private String check1Reason;
    private long check1Period;
    private String check2;
    private String check2Status;
    private String check2Reason;
    private long check2Period;
    private String check3;
    private String check3Status;
    private String check3Reason;
    private long check3Period;
    private String outCode;
    private boolean resumed;

    public long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(long leaveId) {
        this.leaveId = leaveId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(long periodStart) {
        this.periodStart = periodStart;
    }

    public long getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(long periodEnd) {
        this.periodEnd = periodEnd;
    }

    public long getPeriodSubmit() {
        return periodSubmit;
    }

    public void setPeriodSubmit(long periodSubmit) {
        this.periodSubmit = periodSubmit;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public String getCheck1() {
        return check1;
    }

    public void setCheck1(String check1) {
        this.check1 = check1;
    }

    public String getCheck1Status() {
        return check1Status;
    }

    public void setCheck1Status(String check1Status) {
        this.check1Status = check1Status;
    }

    public String getCheck1Reason() {
        return check1Reason;
    }

    public void setCheck1Reason(String check1Reason) {
        this.check1Reason = check1Reason;
    }

    public long getCheck1Period() {
        return check1Period;
    }

    public void setCheck1Period(long check1Period) {
        this.check1Period = check1Period;
    }

    public String getCheck2() {
        return check2;
    }

    public void setCheck2(String check2) {
        this.check2 = check2;
    }

    public String getCheck2Status() {
        return check2Status;
    }

    public void setCheck2Status(String check2Status) {
        this.check2Status = check2Status;
    }

    public String getCheck2Reason() {
        return check2Reason;
    }

    public void setCheck2Reason(String check2Reason) {
        this.check2Reason = check2Reason;
    }

    public long getCheck2Period() {
        return check2Period;
    }

    public void setCheck2Period(long check2Period) {
        this.check2Period = check2Period;
    }

    public String getCheck3() {
        return check3;
    }

    public void setCheck3(String check3) {
        this.check3 = check3;
    }

    public String getCheck3Status() {
        return check3Status;
    }

    public void setCheck3Status(String check3Status) {
        this.check3Status = check3Status;
    }

    public String getCheck3Reason() {
        return check3Reason;
    }

    public void setCheck3Reason(String check3Reason) {
        this.check3Reason = check3Reason;
    }

    public long getCheck3Period() {
        return check3Period;
    }

    public void setCheck3Period(long check3Period) {
        this.check3Period = check3Period;
    }

    public String getOutCode() {
        return outCode;
    }

    public void setOutCode(String outCode) {
        this.outCode = outCode;
    }

    public boolean isResumed() {
        return resumed;
    }

    public void setResumed(boolean resumed) {
        this.resumed = resumed;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "leaveId=" + leaveId +
                ", userId='" + userId + '\'' +
                ", periodStart=" + periodStart +
                ", periodEnd=" + periodEnd +
                ", periodSubmit=" + periodSubmit +
                ", leaveType='" + leaveType + '\'' +
                ", leaveReason='" + leaveReason + '\'' +
                ", destination='" + destination + '\'' +
                ", status=" + status +
                ", finish=" + finish +
                ", urgent=" + urgent +
                ", check1='" + check1 + '\'' +
                ", check1Status='" + check1Status + '\'' +
                ", check1Reason='" + check1Reason + '\'' +
                ", check1Period=" + check1Period +
                ", check2='" + check2 + '\'' +
                ", check2Status='" + check2Status + '\'' +
                ", check2Reason='" + check2Reason + '\'' +
                ", check2Period=" + check2Period +
                ", check3='" + check3 + '\'' +
                ", check3Status='" + check3Status + '\'' +
                ", check3Reason='" + check3Reason + '\'' +
                ", check3Period=" + check3Period +
                ", outCode='" + outCode + '\'' +
                ", resumed=" + resumed +
                '}';
    }
}
