package org.lba.android.simple.trainer.db.model;

import  java.util.Date;

public class SettingsModel {

    public enum SettingsModelFields { SETTINGS, ID, COLUMN1, COLUMN2, COLUMN3, DATECOLUMN };
    private String id;
    private String column1;
    private String column2;
    private long column3;
    private Date dateColumn;

    public SettingsModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public long getColumn3() {
        return column3;
    }

    public void setColumn3(long column3) {
        this.column3 = column3;
    }

    public Date getDateColumn() {
        return dateColumn;
    }

    public void setDateColumn(Date dateColumn) {
        this.dateColumn = dateColumn;
    }

    @Override
    public String toString() {
        return "SettingsModel{" +
                "id='" + id + '\'' +
                ", column1='" + column1 + '\'' +
                ", column2='" + column2 + '\'' +
                ", column3=" + column3 +
                ", dateColumn=" + dateColumn +
                '}';
    }
}
