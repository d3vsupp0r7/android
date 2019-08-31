package org.sglba.trainman.db.room.config;

import android.arch.persistence.room.Room;
import android.content.Context;

public class RoomDatabaseClientConfig {

    private Context mCtx;
    private static RoomDatabaseClientConfig mInstance;

    private AppDatabase appDatabase;

    private RoomDatabaseClientConfig(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //EmployeeRoomDB is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "StationsRoomDB")
                .allowMainThreadQueries()//Addded for example purpose. Use AsyncTask for production
                .build();
    }

    public static synchronized RoomDatabaseClientConfig getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new RoomDatabaseClientConfig(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
