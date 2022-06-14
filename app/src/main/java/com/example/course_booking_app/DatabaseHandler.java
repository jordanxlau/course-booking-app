package com.example.course_booking_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.util.ArrayList;

//This class handles the database
public class DatabaseHandler extends SQLiteOpenHelper {

    //Initializations for Users table
    public static final String USER_TABLE_NAME = "users"; //Table name
    public static final String USER_PRIMARY_KEY = "usersID"; //Primary Key
    public static final String USER_COL_NAME = "username"; //First column name (user names)
    public static final String USER_COL_PASS = "password"; //Second column name (user passwords)
    public static final String USER_COL_TYPE = "userType"; //Third column name ("admin", "student" or "instructor")

    //Initializations for Courses table
    public static final String COURSE_TABLE_NAME = "courses"; //Table name
    public static final String COURSE_PRIMARY_KEY = "coursesID"; //Primary Key
    public static final String COURSE_COL_CODE = "courseCode"; //First column name (course codes)
    public static final String COURSE_COL_NAME = "courseName"; //Second column name (course names)
    public static final String COURSE_COL_INSTRUCTOR = "courseInstructor"; //Third column name (instructor names)

    public DatabaseHandler(Context context){
        super(context, "users4.db", null, 1);
    }

    @Override //"CREATE TABLE" Creates a table automagically when constructor is called
    public void onCreate(SQLiteDatabase db) {
        //Users table
        String createUsers = "CREATE TABLE " + USER_TABLE_NAME
                + "("
                + USER_PRIMARY_KEY + " INTEGER " + "PRIMARY KEY,"
                + USER_COL_NAME + " STRING, "
                + USER_COL_PASS + " STRING, "
                + USER_COL_TYPE + " STRING"
                + ")";

        //Courses table
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

    @Override //"DROP" Removes both tables (never called?)
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String upgrade = "DROP TABLE IF EXISTS " + USER_TABLE_NAME;
        db.execSQL(upgrade);
        upgrade = "DROP TABLE IF EXISTS " + COURSE_TABLE_NAME;
        db.execSQL(upgrade);
    }

    //Gets users in the form of ArrayList
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

    //Gets courses in the form of ArrayList
    public ArrayList<CourseModal> getCourses(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + COURSE_TABLE_NAME, null);

        ArrayList<CourseModal> courseModalArrayList = new ArrayList<>();

        if(cursorCourses.moveToFirst()){
            do{
                courseModalArrayList.add(new CourseModal(cursorCourses.getString(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3)));
            } while(cursorCourses.moveToNext());
        }

        cursorCourses.close();
        return courseModalArrayList;
    }

    //Adds a user to the Users table
    public boolean addUser(String username, String password, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        boolean result = false;

        if (this.findPassword(username) == null && !type.equals("--Select account type for creation--")) { //cannot find a password associated with that user (the user doesn't yet exist)
            values.put(USER_COL_NAME, username);
            values.put(USER_COL_PASS, password);
            values.put(USER_COL_TYPE, type);
            db.insert(USER_TABLE_NAME, null, values);
            result = true;
        } else if (type.equals("--Select account type for creation--")){//Account type not selected yet
            MainActivity.toast.makeText(MainActivity.context, "Please select account type!", MainActivity.duration).show();
        } else if (this.findPassword(username) != null){//User already exists
            MainActivity.toast.makeText(MainActivity.context, "User already exists!", MainActivity.duration).show();
        }

        return result;
    }


    //Adds a course to the courses table
    public boolean addCourse(String courseCode, String courseName, String courseInstructor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        boolean result = false;

        values.put(COURSE_COL_CODE, courseCode);
        values.put(COURSE_COL_NAME, courseName);
        values.put(COURSE_COL_INSTRUCTOR, courseInstructor);
        db.insert(COURSE_TABLE_NAME, null, values);
        result = true;

        return result;
    }

    //modify a course
    public boolean modifyCourse(String identifier, String courseCode, String courseName, String courseInstructor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        boolean result = false;

        values.put(COURSE_COL_CODE, courseCode);
        values.put(COURSE_COL_NAME, courseName);
        values.put(COURSE_COL_INSTRUCTOR, courseInstructor);
        db.update(COURSE_TABLE_NAME, values, COURSE_PRIMARY_KEY + "=?", new String[]{identifier});
        result = true;

        return result;
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
        }

        cursor.close();
        //If user doesn't match any in database, foundType will be null
        return foundType;
    }

    //Public method deleting a user from the database. Returns true if successfully deleted.
    //NOTE: currently no password needed to delete an account
    public boolean removeUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        boolean result = false;

        //SELECT * FROM users WHERE "username" = "username"
        String query = "SELECT * FROM "+ USER_TABLE_NAME + " WHERE " + USER_COL_NAME + " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            result = true;
            db.delete(USER_TABLE_NAME, USER_COL_NAME + "=?", new String[]{username});
            cursor.close();
        }

        return result;
    }

    //Public method deleting a course from the database. Returns true if successfully deleted.
    public boolean removeCourse(String courseID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        boolean result = false;

        //SELECT * FROM courses WHERE "courseName" = "courseName"
        String query = "SELECT * FROM "+ COURSE_TABLE_NAME + " WHERE " + COURSE_PRIMARY_KEY + " = \"" + courseID + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            result = true;
            db.delete(COURSE_TABLE_NAME, COURSE_PRIMARY_KEY + "=?", new String[]{courseID});
            cursor.close();
        }
        return result;
    }

}