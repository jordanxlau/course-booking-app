package com.example.course_booking_app;

import java.util.ArrayList;

public class User {
    private String ID;
    private String username;
    private String password;
    private String usertype;
    private int[] timeBlock;

    public User(String ID, String username, String password, String usertype) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
        this.timeBlock = new int[15];

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Boolean isTimeAvailible(Integer n){
        for(int i = 0; i<14 ;i++){
            if (this.timeBlock[i] == n)
                return false;
        }
        return true;
    }

    public void addTime(Integer n){

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
