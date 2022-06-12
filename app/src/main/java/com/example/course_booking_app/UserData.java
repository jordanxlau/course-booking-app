package com.example.course_booking_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

//This class handles the database
public class UserData extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "users2"; //Table name
    public static final String PRIMARY_KEY = "usersID"; //Primary Key
    public static final String COL_NAME = "username"; //First column name (user names)
    public static final String COL_PASS = "password"; //Second column name (user passwords)

    public UserData(Context context){
        super(context, "users2.db", null, 1);
    }

    @Override //"CREATE TABLE" Creates a table automagically when constructor is called?
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + TABLE_NAME
                + "("
                + PRIMARY_KEY + " INTEGER " + "PRIMARY KEY,"
                + COL_NAME + " STRING, "
                + COL_PASS + " STRING "
                + ")";
        db.execSQL(create);
    }

    @Override //"DROP" Removes a table
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String upgrade = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(upgrade);
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null); // returns "cursor" all products from the table
    }

    public void addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NAME, username);
        values.put(COL_PASS, password);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //Finds the password of a certain user
    public String findPassword(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        //SELECT * FROM users.db WHERE username = "user"
        String query = "SELECT " + COL_PASS + " FROM " + TABLE_NAME + " WHERE " + COL_NAME + " = \"" + user + "\"";
        Cursor cursor = db.rawQuery(query, null);
        String foundPassword = null;

        if(cursor.moveToFirst()){//If password matches
            foundPassword = cursor.getString(0);
            System.out.println(foundPassword);
        }

        cursor.close();

        //If user doesn't match any in database, foundPassword will be null
        return foundPassword;

    }

}