package org.lba.android.simple.trainer.db.room.config;

import android.content.Context;

import androidx.room.Room;

public class RoomDatabaseClient {

    private Context mCtx;
    private static RoomDatabaseClient mInstance;

    private AppDatabase appDatabase;

    private RoomDatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //EmployeeRoomDB is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "EmployeeRoomDB").build();
    }

    public static synchronized RoomDatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new RoomDatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
