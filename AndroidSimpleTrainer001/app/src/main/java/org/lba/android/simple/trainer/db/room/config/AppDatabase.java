package org.lba.android.simple.trainer.db.room.config;

import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import org.lba.android.simple.trainer.db.model.EmployeeModelWithRoomDAO;

import org.lba.android.simple.trainer.db.model.EmployeeModelWithRoom;

@Database(entities = {EmployeeModelWithRoom.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EmployeeModelWithRoomDAO employeeDao();

}
