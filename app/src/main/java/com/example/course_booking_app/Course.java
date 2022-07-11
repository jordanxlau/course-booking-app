package com.example.course_booking_app;

public class Course {
    private String ID;
    private String code;
    private String name;
    private String instructor;
    private String description;
    private String days;
    private String hours;
    private Integer capacity;

    public Course(String ID, String code, String name, String instructor) {
        this.ID = ID;
        this.code = code;
        this.name = name;
        this.instructor = instructor;
        this.description = "";
        this.capacity = 0;
        this.days = "";
        this.hours = "";


    }

    public String getID() {return ID;}

    public String getDays() {return days;}

    public String getHours() {return hours;}

    public String getDescription() {return description;}

    public Integer getCapacity() {return capacity;}

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

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void incrementCapacity() {
        this.capacity++;
    }

    public void resetCourse(){
        this.resetDescription();
        this.resetCapacity();
        this.resetDays();
        this.resetHours();
    }

    public void resetDescription() {
        this.description = "";
    }

    public void resetCapacity() {
        this.capacity = 0;
    }

    public void resetDays() {this.days = "";}

    public void resetHours() {this.hours = "";}

}
