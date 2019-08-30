package org.lba.android.simple.trainer.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import org.lba.android.simple.trainer.db.model.EmployeeModelWithRoom;

import java.util.List;

@Dao
public interface EmployeeModelWithRoomDAO {

    @Query("SELECT * FROM employee_with_room")
    List<EmployeeModelWithRoom> getAll();
}
