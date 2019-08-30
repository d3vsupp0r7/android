package org.lba.android.simple.trainer.db.room.config;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.lba.android.simple.trainer.db.dao.EmployeeModelWithRoomDAO;

import org.lba.android.simple.trainer.db.model.EmployeeModelWithRoom;

@Database(entities = {EmployeeModelWithRoom.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EmployeeModelWithRoomDAO employeeDao();

}
