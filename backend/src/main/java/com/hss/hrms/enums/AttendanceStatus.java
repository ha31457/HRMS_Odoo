package com.hss.hrms.enums;

public enum AttendanceStatus {
    Present, Absent, Half_Day("Half-Day"), Leave;

    private String displayName;

    AttendanceStatus() {
        this.displayName = this.name();
    }

    AttendanceStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
