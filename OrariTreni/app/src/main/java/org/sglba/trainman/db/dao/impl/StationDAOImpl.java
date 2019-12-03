package org.sglba.trainman.db.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.sglba.trainman.db.DatabaseHelper;
import org.sglba.trainman.db.dao.StationDAO;
import org.sglba.trainman.db.model.StationEntity;
import org.sglba.trainman.model.Station;

import java.util.ArrayList;
import java.util.List;

public class StationDAOImpl implements StationDAO {

    private static final String LOG = StationDAOImpl.class.getName();

    protected SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context mContext;

    public StationDAOImpl(Context context) {
        this.mContext = context;
        dbHelper = DatabaseHelper.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DatabaseHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public long createStation(StationEntity model) {

        ContentValues values = new ContentValues();
        values.put(StationEntity.StationEntityFields.BUSINESS_ID.toString(), model.getBusinessId());
        values.put(StationEntity.StationEntityFields.FULL_STATION_NAME.toString(), model.getFullStationName());
        values.put(StationEntity.StationEntityFields.LATITUDE.toString(), model.getLatitude());
        values.put(StationEntity.StationEntityFields.LONGITUDE.toString(),model.getLongitude());
        values.put(StationEntity.StationEntityFields.STATION_NAME.toString(),model.getStationName());

        // insert row
        long id = database.insert(StationEntity.StationEntityFields.STATION.toString(), null, values);

        return id;
    }

    @Override
    public List<StationEntity> readAllStations() {

        List<StationEntity> settings = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + StationEntity.StationEntityFields.STATION.toString();

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                StationEntity station = new StationEntity();
                station.setId(c.getLong(c.getColumnIndex(StationEntity.StationEntityFields.ID.toString())));
                station.setBusinessId(c.getString(c.getColumnIndex(StationEntity.StationEntityFields.BUSINESS_ID.toString())));
                station.setFullStationName(c.getString(c.getColumnIndex(StationEntity.StationEntityFields.FULL_STATION_NAME.toString())));
                station.setLatitude(c.getString(c.getColumnIndex(StationEntity.StationEntityFields.LATITUDE.toString())));
                station.setLongitude(c.getString(c.getColumnIndex(StationEntity.StationEntityFields.LONGITUDE.toString())));
                station.setStationName(c.getString(c.getColumnIndex(StationEntity.StationEntityFields.STATION_NAME.toString())));
                // adding to list
                settings.add(station);
            } while (c.moveToNext());
        }

        return settings;
    }

    @Override
    public long updateStation(StationEntity model) {
        return 0;
    }

    @Override
    public long updateStationById(long id) {
        return 0;
    }

    @Override
    public Station deleteStationById() {
        return null;
    }
}
