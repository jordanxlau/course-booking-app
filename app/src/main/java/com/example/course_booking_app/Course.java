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
        this.description = "No course description set!";
        this.capacity = 0;
        this.days = "No days set!";
        this.hours = "No hours set!";


    }

    public String getID() {return ID;}

    public String getDays() {return days;}

    public String getHours() {return hours;}

    public void setDays(String days) {
        this.days = days;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setDaysToDefault() {this.days = "No days set!";}

    public void setHoursToDefault() {this.hours = "No hours set!";}

    public String getDescription() {return description;}

    public Integer getCapacity() {return capacity;}

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDescriptionToDefault() {
        this.description = "No course description set!";
    }

    public void setCapacityToZero() {
        this.capacity = 0;
    }

    public void increaseCapacityByOne() {
        this.capacity++;
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
