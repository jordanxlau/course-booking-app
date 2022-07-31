package com.example.course_booking_app;

public class User {
    private String ID;
    private String username;
    private String password;
    private String usertype;

    public User(String ID, String username, String password, String usertype) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
