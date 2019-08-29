package org.lba.android.simple.trainer.db.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.lba.android.simple.trainer.db.DatabaseHelper;
import org.lba.android.simple.trainer.model.Employee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EmployeeDAO {

    protected SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context mContext;

    private static final String LOG = EmployeeDAO.class.getName();

    public EmployeeDAO(Context context) {
        this.mContext = context;
        dbHelper = DatabaseHelper.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DatabaseHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

    //Create
    public long createEmployeeRecord(EmployeeModel model) {

        ContentValues values = new ContentValues();
        values.put(EmployeeModel.EmployeeModelFields.NAME.toString(), model.getName());
        values.put(EmployeeModel.EmployeeModelFields.SURNAME.toString(), model.getSurname());

        // insert row
        long id = database.insert(EmployeeModel.EmployeeModelFields.EMPLOYEE.toString(), null, values);

        return id;
    }

    //Read
    public List<EmployeeModel> readAllEmployee() {

        List<EmployeeModel> employeeList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + EmployeeModel.EmployeeModelFields.EMPLOYEE.toString();

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                EmployeeModel td = new EmployeeModel();
                td.setId(c.getString(c.getColumnIndex(EmployeeModel.EmployeeModelFields.ID.toString())));
                td.setName(c.getString(c.getColumnIndex(EmployeeModel.EmployeeModelFields.NAME.toString())));
                td.setSurname(c.getString(c.getColumnIndex(EmployeeModel.EmployeeModelFields.SURNAME.toString())));
                // adding to list
                employeeList.add(td);
            } while (c.moveToNext());
        }

        return employeeList;
    }

    //TODO: Update

    //TODO: Delete

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
