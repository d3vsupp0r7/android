package org.sglba.trainman.db.dao;

import org.sglba.trainman.db.model.StationEntity;
import org.sglba.trainman.model.Station;

import java.util.List;

public interface StationDAO {

    //Create
    public long createStation(StationEntity model);

    //Read
    public List<StationEntity> readAllStations();

    //Update
    public long updateStation(StationEntity model);
    public long updateStationById(long id);

    //Delete
    public Station deleteStationById();
}
