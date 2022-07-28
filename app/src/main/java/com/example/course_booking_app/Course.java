package com.example.course_booking_app;

import java.util.ArrayList;

public class Course {
    private String ID;
    private String code;
    private String name;
    private String instructor;
    private Integer timeBlock;
    public static ArrayList<String> studentList;
    public String stuff;
    public Integer i;

    public Course(String ID, String code, String name, String instructor) {
        this.ID = ID;
        this.code = code;
        this.name = name;
        this.instructor = instructor;
        this.timeBlock = 0;
        this.studentList = new ArrayList<>();
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
    //For Testing purposes
    public String printCourses(){
        studentList.trimToSize();
        i = studentList.size();
        stuff = "";
        for (int y = 0; y<i; y++) {
            stuff = stuff + studentList.get(y);
        }
        return stuff;
    }

    public void addStudent(String student){
        studentList.add(student);
        studentList.trimToSize();
    }

    public void removeStudent(String student){
        studentList.remove(student);
        studentList.trimToSize();
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
