package com.elm.shj.admin.portal.services.dto;

public enum ENotificationType {

    FYI("FYI"),
    FYA("FYA"),
    ALL("ALL");

    private String type;

    ENotificationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
