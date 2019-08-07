package org.sglba.trainman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String dbname="Railroad.db";
    public static final String tablename="Station";
    public static final String col1="ID";
    public static final String col2="NAME";
    public DatabaseHelper(Context context) {
        super(context, dbname, null, 1);
    }
    //Database Creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ tablename+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tablename);
        onCreate(db);

    }
    //Database insertion method
//Takes in input Station name and insert in DB
    public boolean insertData(String name){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,name);
        long result = db.insert(tablename,null,contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }
    // Database update method
// If you wanted to make a change in the DB , we could use the id and station name
    public boolean updateData(String id,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,name);
        db.update(tablename, contentValues, "ID = ?",new String[] { id });
        return true;
    }
    // database delete method
// simple delete by id
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tablename, "ID = ?",new String[] {id});
    }
    // database data view
    public Cursor print(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query( tablename ,null,null,null,null,null,null);
        return c;
    }
}
