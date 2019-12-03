package org.sglba.trainman.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "stations")
public class StationEntityRoom implements Serializable {


    private static final long serialVersionUID = -2067362331207212073L;

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "station_id")
    private String stationId;

    @ColumnInfo(name = "station_business_id")
    private String stationBusinessId;

    @ColumnInfo(name = "full_station_name")
    private String fullStationName;

    @ColumnInfo(name = "station_latitude")
    private Double latitude;

    @ColumnInfo(name = "station_longitude")
    private  Double longitude;

    @ColumnInfo(name = "station_name")
    private  String stationName;

    public StationEntityRoom() {
    }

    /**
     *
     * @param stationId
     * @param stationBusinessId
     * @param fullStationName
     * @param latitude
     * @param longitude
     */
    public StationEntityRoom(String stationId, String stationBusinessId, String fullStationName, Double latitude, Double longitude,String stationName) {
        this.stationId = stationId;
        this.stationBusinessId = stationBusinessId;
        this.fullStationName = fullStationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.stationName=stationName;
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

    public String getFullStationName() {
        return fullStationName;
    }

    public void setFullStationName(String fullStationName) {
        this.fullStationName = fullStationName;
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

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public String toString() {
        return "StationEntityRoom{" +
                "id=" + id +
                ", stationId='" + stationId + '\'' +
                ", stationBusinessId='" + stationBusinessId + '\'' +
                ", fullStationName='" + fullStationName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", stationName='" + stationName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationEntityRoom station = (StationEntityRoom) o;
        return fullStationName.equals(station.fullStationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullStationName);
    }

}
