package org.sglba.trainman.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.sglba.trainman.db.model.StationEntity;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME ="Trainman.db";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_STATION = "CREATE TABLE "
            + StationEntity.StationEntityFields.STATION + "("
            + StationEntity.StationEntityFields.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + StationEntity.StationEntityFields.BUSINESS_ID + " TEXT,"
            + StationEntity.StationEntityFields.STATION_NAME + " TEXT,"
            + StationEntity.StationEntityFields.LATITUDE + " TEXT,"
            + StationEntity.StationEntityFields.LONGITUDE + " TEXT"
            + ")";
    /**/
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context);
        return instance;
    }
    /**/
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    //Database Creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + StationEntity.StationEntityFields.STATION);
        // create new tables
        onCreate(db);
    }

}
