package org.sglba.trainman.db.room.config;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import org.sglba.trainman.db.model.StationEntityRoom;
import org.sglba.trainman.db.room.StationEntityRoomDAO;

@Database(entities = {StationEntityRoom.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract StationEntityRoomDAO stationDao();
}
