package com.example.vision;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vision.Model.User;

import java.util.ArrayList;
import java.util.List;

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

    public List<User> getAuthenData(){
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT * FROM User";

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                User user = new User();
                user.set_id(cursor.getString(0));
                user.setName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setUsername(cursor.getString(3));
                user.setPassword(cursor.getString(4));

                userList.add(user);
            }while (cursor.moveToNext());
        }
        return userList;
    }

    public void dropTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE "+TABLE_NAME);
        onCreate(db);
}



}
