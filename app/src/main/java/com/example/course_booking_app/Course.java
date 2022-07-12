package com.example.course_booking_app;

public class Course {
    private String ID;
    private String code;
    private String name;
    private String instructor;
    private String description;
    private String days;
    private String hours;
    private String capacity;

    public Course(String ID, String code, String name, String instructor) {
        this.ID = ID;
        this.code = code;
        this.name = name;
        this.instructor = instructor;
        this.description = "";
        this.capacity = "0";
        this.days = "";
        this.hours = "";
    }

    //overload constructor
    public Course(String ID, String code, String name, String instructor, String description, String capacity, String days, String hours){
        this.ID = ID;
        this.code = code;
        this.name = name;
        this.instructor = instructor;
        this.description = description;
        this.capacity = capacity;
        this.days = days;
        this.hours = hours;
    }

    public String getID() {return ID;}

    public String getDays() {return days;}

    public String getHours() {return hours;}

    public String getDescription() {return description;}

    public String getCapacity() {return capacity;}

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCapacity(String capacity){
        this.capacity = capacity;
    }


    public void resetCourse(){
        this.setDescription("");
        this.setCapacity("0");
        this.setDays("");
        this.setHours("");
        this.setInstructor("");
    }

}
