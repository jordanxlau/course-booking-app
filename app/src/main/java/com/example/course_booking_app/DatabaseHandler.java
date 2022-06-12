package com.example.course_booking_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.util.ArrayList;

//This class handles the database
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String USER_TABLE_NAME = "users"; //Table name
    public static final String USER_PRIMARY_KEY = "usersID"; //Primary Key
    public static final String USER_COL_NAME = "username"; //First column name (user names)
    public static final String USER_COL_PASS = "password"; //Second column name (user passwords)
    public static final String USER_COL_TYPE = "userType"; //Third column name ("admin", "student" or "instructor")

    public static final String COURSE_TABLE_NAME = "courses";
    public static final String COURSE_PRIMARY_KEY = "coursesID";
    public static final String COURSE_COL_CODE = "courseCode";
    public static final String COURSE_COL_NAME = "courseName";
    public static final String COURSE_COL_INSTRUCTOR = "courseInstructor";

    public DatabaseHandler(Context context){
        super(context, "users4.db", null, 1);
    }

    @Override //"CREATE TABLE" Creates a table automagically when constructor is called?
    public void onCreate(SQLiteDatabase db) {
        String createUsers = "CREATE TABLE " + USER_TABLE_NAME
                + "("
                + USER_PRIMARY_KEY + " INTEGER " + "PRIMARY KEY,"
                + USER_COL_NAME + " STRING, "
                + USER_COL_PASS + " STRING, "
                + USER_COL_TYPE + " STRING"
                + ")";

        String createCourses = "CREATE TABLE " + COURSE_TABLE_NAME
                + "("
                + COURSE_PRIMARY_KEY + " INTEGER " + "PRIMARY KEY,"
                + COURSE_COL_CODE + " STRING, "
                + COURSE_COL_NAME + " STRING, "
                + COURSE_COL_INSTRUCTOR + " STRING"
                + ")";

        db.execSQL(createUsers);
        db.execSQL(createCourses);
    }

    @Override //"DROP" Removes a table
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String upgrade = "DROP TABLE IF EXISTS " + USER_TABLE_NAME;
        db.execSQL(upgrade);
    }

//    public Cursor getData(){
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String query = "SELECT * FROM " + TABLE_NAME;
//        return db.rawQuery(query, null); // returns "cursor" all products from the table
//    }

    public ArrayList<UserModal> getUsers(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME, null);

        ArrayList<UserModal> userModalArrayList = new ArrayList<>();

        if(cursorUsers.moveToFirst()){
            do{
                userModalArrayList.add(new UserModal(cursorUsers.getString(0),
                                            cursorUsers.getString(1),
                                            cursorUsers.getString(2),
                                            cursorUsers.getString(3)));
            } while(cursorUsers.moveToNext());
        }

        cursorUsers.close();
        return userModalArrayList;

    }

    public void addUser(String username, String password, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        System.out.println("TRACE ADDUSER()");

        //Only adds the user if it cannot find a password associated with that user (and therefore the user doesn't yet exist)
        if (this.findPassword(username) == null) {
            values.put(USER_COL_NAME, username);
            values.put(USER_COL_PASS, password);
            values.put(USER_COL_TYPE, type);

            db.insert(USER_TABLE_NAME, null, values);
        }
        db.close();
    }

    //Finds the password of a certain user
    public String findPassword(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        //SELECT "password" FROM users.db WHERE username = "user"
        String query = "SELECT " + USER_COL_PASS + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COL_NAME + " = \"" + user + "\"";
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

    //Finds the password of a certain user
    public String findUserType(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        //SELECT "userType" FROM users.db WHERE username = "user"
        String query = "SELECT " + USER_COL_TYPE + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COL_NAME + " = \"" + user + "\"";
        Cursor cursor = db.rawQuery(query, null);
        String foundType = null;

        if(cursor.moveToFirst()){//If password matches
            foundType = cursor.getString(0);
            System.out.println(foundType);
        }

        cursor.close();

        //If user doesn't match any in database, foundType will be null
        return foundType;

    }
}