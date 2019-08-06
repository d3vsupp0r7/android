package org.sglba.trainman.costraints;

public enum TrainCategoryCostraintsEnum {

    SFM("SFM","Metro", "MET"),
    REGIONALE("Regionale","Regionale","REG"),
    URB("","Urb",""),
    AUTOBUS("Autobus","Autobus","Autobus"),
    ICN("ICN","ICN","ICN"),
    IC("IC","IC","IC"),
    FRECCIAROSSA("Frecciarossa","Frecciarossa","FR"),
    FRECCIABIANCA("Frecciabianca","Frecciabianca","FB"),
    FRECCIAARGENTO("Frecciaargento","Frecciaargento","FA"),
    INVALID_VALUE("INVALID_VALUE","INVALID_VALUE","INVALID_VALUE")
    ;

    private String code;
    private String type;
    private String description;

    TrainCategoryCostraintsEnum(String code, String type, String description)
    {
        this.code = code;
        this.type = type;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public static TrainCategoryCostraintsEnum getEnumFromCode(String value) {

        TrainCategoryCostraintsEnum result = TrainCategoryCostraintsEnum.INVALID_VALUE;

        for (TrainCategoryCostraintsEnum d: TrainCategoryCostraintsEnum.values()) {
            if(value.equalsIgnoreCase(d.getCode()) ) {
                result = d;
            }
        }

        return result;
    }

    public static TrainCategoryCostraintsEnum getFromEnumKey(String enumKey) {
        TrainCategoryCostraintsEnum result = TrainCategoryCostraintsEnum.INVALID_VALUE;

        for (TrainCategoryCostraintsEnum d: TrainCategoryCostraintsEnum.values()) {
            if(enumKey.equalsIgnoreCase(d.name()) ) {
                result = TrainCategoryCostraintsEnum.valueOf(enumKey.toUpperCase());
            }
        }

        return result;
    }
}
