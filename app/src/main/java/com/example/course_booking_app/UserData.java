package com.example.course_booking_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

//This class handles the database
public class UserData extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "users";
    public static final String PRIMARY_KEY = "usersID";
    public static final String COL_USER = "user"; //first column name

    public UserData(Context context){
        super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + TABLE_NAME + "("
                + PRIMARY_KEY + " INTEGER PRIMARY KEY,"
                + COL_USER + " TEXT "
                + ")";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String upgrade = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(upgrade);
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null); // returns "cursor" all products from the table
    }

    public void addUser(){

    }

}