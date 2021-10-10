package com.elm.shj.admin.portal.services.dto;

public enum ENotificationSeverity {

    IMPORTANT("important"),
    NORMAL("normal"),
    ALL("all");

    private String severity;

    ENotificationSeverity(String severity) {
        this.severity = severity;
    }

    public String getSeverity() {
        return severity;
    }
}
