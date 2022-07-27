package com.example.course_booking_app;

import java.util.ArrayList;

public class Course {
    private String ID;
    private String code;
    private String name;
    private String instructor;
    private Integer timeBlock;
    public static ArrayList<String> studentList;


    public Course(String ID, String code, String name, String instructor) {
        this.ID = ID;
        this.code = code;
        this.name = name;
        this.instructor = instructor;
        this.timeBlock = 0;
        studentList = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Boolean isStudentEnrolled(String student){
        if(studentList.contains(student))
            return true;
        return false;
    }

    public void addStudent(String student){
        this.studentList.add(student);
    }

    public void removeStudent(String student){
        this.studentList.remove(student);
    }

    public Integer getTime() {
        return timeBlock;
    }

    public void setTime(Integer timeBlock) {
        this.timeBlock = timeBlock;
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
