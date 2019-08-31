package org.sglba.trainman.db.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.sglba.trainman.db.model.StationEntityRoom;

import java.util.List;

@Dao
public interface StationEntityRoomDAO {

    //Create
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(StationEntityRoom station);

    //Read
    @Query("SELECT * FROM stations")
    List<StationEntityRoom> getAll();

    @Query("SELECT * FROM stations WHERE id = :stationsId")
    StationEntityRoom findStationById(long stationsId);

    //Update
    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(StationEntityRoom station);

    //Delete
    @Query("DELETE FROM stations")
    public void deleteAll();

    @Query("DELETE FROM stations WHERE id = :stationsId")
    abstract void deleteStationById(long stationsId);
}
