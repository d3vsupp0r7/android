package org.lba.android.simple.trainer.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import org.lba.android.simple.trainer.db.model.EmployeeModel;
import org.lba.android.simple.trainer.db.model.SettingsModel;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String dbname="trainerdb.db";

    /*Table*/
    public static final String tablename="Settings";
    public static final String tablenameEmployee= EmployeeModel.EmployeeModelFields.EMPLOYEE.toString();
    // Database Version
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_SETTINGS = "CREATE TABLE "
            + SettingsModel.SettingsModelFields.SETTINGS + "("
            + SettingsModel.SettingsModelFields.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SettingsModel.SettingsModelFields.COLUMN1 + " TEXT,"
            + SettingsModel.SettingsModelFields.COLUMN2 + " TEXT,"
            + SettingsModel.SettingsModelFields.COLUMN3 + " INTEGER,"
            + SettingsModel.SettingsModelFields.DATECOLUMN + " DATETIME" + ")";
    /*Employee*/
    private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE "
            + EmployeeModel.EmployeeModelFields.EMPLOYEE + "("
            + EmployeeModel.EmployeeModelFields.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EmployeeModel.EmployeeModelFields.NAME + " TEXT,"
            + EmployeeModel.EmployeeModelFields.SURNAME + " TEXT )";
    /**/
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context);
        return instance;
    }
    /**/
    public DatabaseHelper(Context context) {
        super(context, dbname, null, 1);
    }
    //Database Creation
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_SETTINGS);
        db.execSQL(CREATE_TABLE_EMPLOYEE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SettingsModel.SettingsModelFields.SETTINGS);
        db.execSQL("DROP TABLE IF EXISTS " + EmployeeModel.EmployeeModelFields.EMPLOYEE);
        // create new tables
        onCreate(db);
    }

}
