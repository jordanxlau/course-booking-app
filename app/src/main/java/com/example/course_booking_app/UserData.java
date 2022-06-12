package com.example.course_booking_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

//This class handles the database
public class UserData extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "users"; //Table name
    public static final String PRIMARY_KEY = "usersID"; //Primary Key
    public static final String COL_NAME = "username"; //First column name (user names)
    public static final String COL_PASS = "password"; //Second column name (user passwords)
    public static final String COL_TYPE = "userType"; //Third column name ("admin", "student" or "instructor")

    public UserData(Context context){
        super(context, "users4.db", null, 1);
    }

    @Override //"CREATE TABLE" Creates a table automagically when constructor is called?
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + TABLE_NAME
                + "("
                + PRIMARY_KEY + " INTEGER " + "PRIMARY KEY,"
                + COL_NAME + " STRING, "
                + COL_PASS + " STRING, "
                + COL_TYPE + " STRING"
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

    //Public method adding a user to the database. Returns true if successfully added.
    public boolean addUser(String username, String password, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        boolean result = false;

        //Only adds the user if:
        // it cannot find a password associated with that user (and therefore the user doesn't yet exist)
        // user has selected an account type
        if (this.findPassword(username) == null && !type.equals("-- Account to Create --")) {
            System.out.println("type: " + type);
            values.put(COL_NAME, username);
            values.put(COL_PASS, password);
            values.put(COL_TYPE, type);
            db.insert(TABLE_NAME, null, values);
            result = true;
        }

        return result;
    }

    //Public method deleting a user from the database. Returns true if successfully deleted.
    //NOTE: currently no password needed to delete
    public boolean removeUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        boolean result = false;

        //SELECT * FROM users WHERE "username" = "username"
        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE " + COL_NAME + " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            //String nameStr = cursor.getString(1);
            result = true;
            db.delete(TABLE_NAME, COL_NAME + "=?", new String[]{username});
            cursor.close();
        }

        return result;
    }

    //Finds the password of a certain user
    public String findPassword(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        String foundPassword = null;

        //SELECT "password" FROM users WHERE "username" = "user"
        String query = "SELECT " + COL_PASS + " FROM " + TABLE_NAME + " WHERE " + COL_NAME + " = \"" + user + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){//If password matches
            foundPassword = cursor.getString(0);
        }

        cursor.close();

        //If user doesn't match any in database, foundPassword will be null
        return foundPassword;
    }

    //Finds the password of a certain user
    public String findUserType(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        //SELECT "userType" FROM users.db WHERE username = "user"
        String query = "SELECT " + COL_TYPE + " FROM " + TABLE_NAME + " WHERE " + COL_NAME + " = \"" + user + "\"";
        Cursor cursor = db.rawQuery(query, null);
        String foundType = null;

        if(cursor.moveToFirst()){//If password matches
            foundType = cursor.getString(0);
        }

        cursor.close();

        //If user doesn't match any in database, foundType will be null
        return foundType;

    }

}