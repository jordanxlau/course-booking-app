package com.example.course_booking_app;

import java.util.ArrayList;

public class User {
    private String ID;
    private String username;
    private String password;
    private String usertype;
    private boolean[] availableBlocks; //Represents availability of all 10 possible course blocks in a standard timetable

    public User(String ID, String username, String password, String usertype) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
        this.availableBlocks = new boolean[]{true, true, true, true, true, true, true, true, true, true};
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isAvailableAtBlock(int block){
        return availableBlocks[block];
    }

    public void addCourseAtBlock(int block){
        this.availableBlocks[block] = false;
    }

    public void removeCourseAtBlock(int block){
        this.availableBlocks[block] = true;
    }

    public String toString(){
        return "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

}
