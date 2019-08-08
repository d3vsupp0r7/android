package org.sglba.trainman.db.model;

public class StationEntity {

    /*
    * First element of map represent the table name
    * */
    public enum StationEntityFields { STATION, ID, BUSINESS_ID, STATION_NAME, LATITUDE, LONGITUDE };

    private Long id;
    private String businessId;
    private String stationName;
    private String latitude;
    private  String longitude;

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

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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

    @Override
    public String toString() {
        return "StationEntity{" +
                "id='" + id + '\'' +
                ", businessId='" + businessId + '\'' +
                ", stationName='" + stationName + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
