package com.example.course_booking_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

//This class handles the database
public class UserData extends SQLiteOpenHelper {

    public final String TABLE_NAME = "users";
    public final String PRIMARY_KEY = "";
    public final String USER = "";

    public UserData(Context context){
        super(context, "products.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + TABLE_NAME + "("
                + PRIMARY_KEY + " INTEGER PRIMARY KEY,"
                + USER + " TEXT "
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
}