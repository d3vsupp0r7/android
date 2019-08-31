package org.sglba.trainman.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "stations")
public class StationEntityRoom {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "station_id")
    private String stationId;

    @ColumnInfo(name = "station_business_id")
    private String stationBusinessId;

    @ColumnInfo(name = "station_name")
    private String stationName;

    @ColumnInfo(name = "station_latitude")
    private Double latitude;

    @ColumnInfo(name = "station_longitude")
    private  Double longitude;

    public StationEntityRoom() {
    }

    /**
     *
     * @param id
     * @param stationId
     * @param stationBusinessId
     * @param stationName
     * @param latitude
     * @param longitude
     */
    public StationEntityRoom(Long id, String stationId, String stationBusinessId, String stationName, Double latitude, Double longitude) {
        this.id = id;
        this.stationId = stationId;
        this.stationBusinessId = stationBusinessId;
        this.stationName = stationName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @param stationId
     * @param stationBusinessId
     * @param stationName
     * @param latitude
     * @param longitude
     */
    public StationEntityRoom(String stationId, String stationBusinessId, String stationName, Double latitude, Double longitude) {
        this.stationId = stationId;
        this.stationBusinessId = stationBusinessId;
        this.stationName = stationName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationBusinessId() {
        return stationBusinessId;
    }

    public void setStationBusinessId(String stationBusinessId) {
        this.stationBusinessId = stationBusinessId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "StationEntityRoom{" +
                "id=" + id +
                ", stationId='" + stationId + '\'' +
                ", stationBusinessId='" + stationBusinessId + '\'' +
                ", stationName='" + stationName + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
