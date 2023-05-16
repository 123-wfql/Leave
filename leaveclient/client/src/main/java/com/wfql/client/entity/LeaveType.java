package com.wfql.client.entity;

public class LeaveType {
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
