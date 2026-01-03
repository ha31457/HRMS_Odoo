package com.hss.hrms.enums;

public enum EmployeeStatus {
    Active, On_Leave("On Leave"), Terminated, Resigned;

    private String displayName;

    EmployeeStatus() {
        this.displayName = this.name();
    }

    EmployeeStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}