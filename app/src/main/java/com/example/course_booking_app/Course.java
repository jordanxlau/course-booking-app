package com.example.course_booking_app;

public class Course {
    private String ID;
    private String code;
    private String name;
    private String instructor;

    public Course(String ID, String code, String name, String instructor) {
        this.ID = ID;
        this.code = code;
        this.name = name;
        this.instructor = instructor;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

}
