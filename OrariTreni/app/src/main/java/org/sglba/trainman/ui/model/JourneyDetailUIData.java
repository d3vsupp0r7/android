package org.sglba.trainman.ui.model;

import java.io.Serializable;

public class JourneyDetailUIData implements Serializable {

    private static final long serialVersionUID = -8830865347138409537L;

    private String departureStationName;
    private String departureHour;
    private String arrivalStationName;
    private String arrivalHour;
    private String journeyDuration;
    private String numberDay;
    private String month;

    public JourneyDetailUIData(){}

    public String getDepartureStationName() {
        return departureStationName;
    }

    public void setDepartureStationName(String departureStationName) {
        this.departureStationName = departureStationName;
    }

    public String getArrivalStationName() {
        return arrivalStationName;
    }

    public void setArrivalStationName(String arrivalStationName) {
        this.arrivalStationName = arrivalStationName;
    }
    public String getDepartureHour() {
        return departureHour;
    }

    public void setDepartureHour(String departureHour) {
        this.departureHour = departureHour;
    }

    public String getArrivalHour() {
        return arrivalHour;
    }

    public void setArrivalHour(String arrivalHour) {
        this.arrivalHour = arrivalHour;
    }

    public String getJourneyDuration() {
        return journeyDuration;
    }

    public void setJourneyDuration(String journeyDuration) {
        this.journeyDuration = journeyDuration;
    }

    public String getNumberDay() {
        return numberDay;
    }

    public void setNumberDay(String numberDay) {
        this.numberDay = numberDay;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "JourneyDetailUIData{" +
                "departureStationName='" + departureStationName + '\'' +
                ", departureHour='" + departureHour + '\'' +
                ", arrivalStationName='" + arrivalStationName + '\'' +
                ", arrivalHour='" + arrivalHour + '\'' +
                ", journeyDuration='" + journeyDuration + '\'' +
                ", numberDay='" + numberDay + '\'' +
                ", month='" + month + '\'' +
                '}';
    }
}
