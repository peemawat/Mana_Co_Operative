package com.example.vision;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vision.Model.User;

public class SQLiteDB extends SQLiteOpenHelper {
    public static final String DB_NAME = "smartdoordb";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "User";
    public static final String COL_Name = "name";
    public static final String COL_Email = "email";
    public static final String COL_Username = "username";
    public static final String COL_Password = "password";
    public static final String COL_ID = "_id";

    public SQLiteDB(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +" (_id VARCHAR PRIMARY KEY, "
                + COL_Name + " VARCHAR, " + COL_Email + " VARCHAR, "
                + COL_Username + " VARCHAR," + COL_Password +" VARCHAR );";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }



    public void insertData2SQLite(User userForSQLite){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, userForSQLite.get_id());
        contentValues.put(COL_Name, userForSQLite.getName());
        contentValues.put(COL_Email, userForSQLite.getEmail());
        contentValues.put(COL_Username, userForSQLite.getUsername());
        contentValues.put(COL_Password, userForSQLite.getPassword());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();

    }

    public void retrieveData(){

    }




}
