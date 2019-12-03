package org.sglba.trainman.db.model;

import java.io.Serializable;

public class StationEntity implements Serializable {

    /*
    * First element of map represent the table name
    * */
    public enum StationEntityFields { STATION, ID, BUSINESS_ID, FULL_STATION_NAME, LATITUDE, LONGITUDE,STATION_NAME };

    private static final long serialVersionUID = 6679549475857037758L;
    private Long id;
    private String businessId;
    private String fullStationName;
    private String latitude;
    private  String longitude;
    private String stationName;

    public StationEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getFullStationName() {
        return fullStationName;
    }

    public void setFullStationName(String fullStationName) {
        this.fullStationName = fullStationName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public String toString() {
        return "StationEntity{" +
                "id=" + id +
                ", businessId='" + businessId + '\'' +
                ", fullStationName='" + fullStationName + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", stationName='" + stationName + '\'' +
                '}';
    }
}
