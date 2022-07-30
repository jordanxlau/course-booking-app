package com.example.course_booking_app;

import java.util.ArrayList;

public class Course {

    private String ID;
    private String code;
    private String name;
    private String instructor;
    private Integer timeBlock;
    private String description;
    public ArrayList<String> studentList;

    public Course(String ID, String code, String name, String instructor) {
        this.ID = ID;
        this.code = code;
        this.name = name;
        this.instructor = instructor;
        this.description = "SET COURSE DESCRIPTION";
        this.timeBlock = 0;
        this.studentList = new ArrayList<>();
    }

    public Course(String ID, String code, String name, String instructor, String description, Integer timeBlock, ArrayList<String> studentList ) {
        this.ID = ID;
        this.code = code;
        this.name = name;
        this.instructor = instructor;
        this.description = description;
        this.timeBlock = timeBlock;
        this.studentList = studentList;
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
        studentList.add(student);
        studentList.trimToSize();
    }

    public void removeStudent(String student){
        studentList.remove(student);
        studentList.trimToSize();
    }

    public ArrayList<String> getStudentList(){
        return this.studentList;
    }

    public Integer getTimeBlock() {
        return timeBlock;
    }

    public void setTimeBlock(Integer timeBlock) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
