package org.lba.android.simple.trainer.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.lba.android.simple.trainer.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.Context;

import android.database.SQLException;
import android.util.Log;

public class SettingsDAO {

    protected SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context mContext;

    private static final String LOG = SettingsDAO.class.getName();

    public SettingsDAO(Context context) {
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
    public long createSettingsRecord(SettingsModel model) {

        ContentValues values = new ContentValues();
        values.put(SettingsModel.SettingsModelFields.COLUMN1.toString(), model.getColumn1());
        values.put(SettingsModel.SettingsModelFields.COLUMN2.toString(), model.getColumn2());
        values.put(SettingsModel.SettingsModelFields.COLUMN3.toString(), model.getColumn3());
        values.put(SettingsModel.SettingsModelFields.DATECOLUMN.toString(), getDateTime());

        // insert row
        long id = database.insert(SettingsModel.SettingsModelFields.SETTINGS.toString(), null, values);

        return id;
    }

    //Read
    public List<SettingsModel> readAllSettings() {

        List<SettingsModel> settings = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + SettingsModel.SettingsModelFields.SETTINGS.toString();

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                SettingsModel td = new SettingsModel();
                td.setId(c.getString(c.getColumnIndex(SettingsModel.SettingsModelFields.ID.toString())));
                td.setColumn1(c.getString(c.getColumnIndex(SettingsModel.SettingsModelFields.COLUMN1.toString())));
                td.setColumn2(c.getString(c.getColumnIndex(SettingsModel.SettingsModelFields.COLUMN2.toString())));
                td.setColumn3(c.getLong(c.getColumnIndex(SettingsModel.SettingsModelFields.COLUMN3.toString())));
                //td.setDateColumn(c.getString(c.getColumnIndex(SettingsModel.SettingsModelFields.DATECOLUMN.toString())));
                // adding to list
                settings.add(td);
            } while (c.moveToNext());
        }

        return settings;
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
