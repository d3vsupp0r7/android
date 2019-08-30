package org.lba.android.simple.trainer.db.model;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeModelWithRoomDAO {

    @Query("SELECT * FROM employee_with_room")
    List<EmployeeModelWithRoom> getAll();
}
