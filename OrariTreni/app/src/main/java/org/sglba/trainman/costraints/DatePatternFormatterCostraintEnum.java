package org.sglba.trainman.costraints;

public enum DatePatternFormatterCostraintEnum {

    US_DATE_PATTERN_WITH_TIME("yyyy-MM-dd'T'HH:mm:ss"),
    US_DATE_PATTERN("yyyy-MM-dd"),
    EU_DATE_PATTERN_WITH_TIME("dd-MM-yyyy'T'HH:mm:ss"),
    EU_DATE_PATTERN_WITH_TIME_NO_T("dd-MM-yyyy HH:mm"),
    EU_DATE_PATTERN("dd-MM-yyyy"),
    ;

    private String value;

    DatePatternFormatterCostraintEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
