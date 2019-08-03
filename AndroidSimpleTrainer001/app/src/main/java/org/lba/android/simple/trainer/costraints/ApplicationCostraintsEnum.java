package org.lba.android.simple.trainer.costraints;

public enum ApplicationCostraintsEnum {

    APP_NAME("AndroidTraining001"),
    ;

    private String value;

    ApplicationCostraintsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
