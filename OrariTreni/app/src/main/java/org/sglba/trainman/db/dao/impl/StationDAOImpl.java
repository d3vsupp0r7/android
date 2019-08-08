package org.sglba.trainman.db.dao.impl;

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
        return 0;
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
                StationEntity td = new StationEntity();
                td.setId(c.getLong(c.getColumnIndex(StationEntity.StationEntityFields.ID.toString())));
                td.setBusinessId(c.getString(c.getColumnIndex(StationEntity.StationEntityFields.BUSINESS_ID.toString())));
                td.setStationName(c.getString(c.getColumnIndex(StationEntity.StationEntityFields.STATION_NAME.toString())));
                td.setLatitude(c.getString(c.getColumnIndex(StationEntity.StationEntityFields.LATITUDE.toString())));
                td.setLongitude(c.getString(c.getColumnIndex(StationEntity.StationEntityFields.LONGITUDE.toString())));
                // adding to list
                settings.add(td);
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
