package org.lba.android.simple.trainer.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.lba.android.simple.trainer.db.model.EmployeeModelWithRoom;

import java.util.List;

@Dao
public interface EmployeeModelWithRoomDAO {

    //Create
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(EmployeeModelWithRoom employee);

    //Read
    @Query("SELECT * FROM employee_with_room")
    List<EmployeeModelWithRoom> getAll();

    @Query("SELECT * FROM employee_with_room WHERE id = :employeeId")
    EmployeeModelWithRoom findEmployeeById(long employeeId);

    //Update
    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(EmployeeModelWithRoom employee);

    //Delete
    @Query("DELETE FROM employee_with_room")
    public void deleteAll();

    @Query("DELETE FROM employee_with_room WHERE id = :employeeId")
    abstract void deleteEmployeeById(long employeeId);
}
