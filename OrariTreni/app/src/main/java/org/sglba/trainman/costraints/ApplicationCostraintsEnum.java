package org.sglba.trainman.costraints;

public enum ApplicationCostraintsEnum {

    APP_NAME("TRAINMAN"),
    ;

    private String value;

    ApplicationCostraintsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
