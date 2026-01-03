package com.hss.hrms.enums;

public enum EmploymentType {
    Full_Time("Full-Time"),
    Part_Time("Part-Time"),
    Contract("Contract"),
    Intern("Intern");

    private String displayName;

    EmploymentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

