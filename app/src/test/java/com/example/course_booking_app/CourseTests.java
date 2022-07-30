package com.example.course_booking_app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit tests for {@Link Course}
 */
public class CourseTests  {
    private Course testCourse;

    @Before
    public void setUp(){
        testCourse = new Course("1234", "ITI1121B", "Software Eng","Eric");
    }

    @Test
    public void test_getCode() {
        assertEquals("ITI1121B", testCourse.getCode());
    }
    @Test
    public void test_getName(){assertEquals("Software Eng",testCourse.getName());}
    @Test
    public void test_getInstructor(){assertEquals("Eric",testCourse.getInstructor());}
    @Test
    public void test_getID(){assertEquals("1234",testCourse.getID());}
    @Test
    public void test_setCode() {
        testCourse.setCode("ITI2101A");
        assertEquals("ITI2101A", testCourse.getCode());
    }
    @Test
    public void test_setName(){
        testCourse.setName("Computer Architecture");
        assertEquals("Computer Architecture",testCourse.getName());}
    @Test
    public void test_setInstructor(){
        testCourse.setInstructor("Jordan");
        assertEquals("Jordan",testCourse.getInstructor());}
    @Test
    public void test_isStudentEnrolled_true(){
        testCourse.addStudent("Jordan Lau");
        assertTrue(testCourse.isStudentEnrolled("Jordan Lau"));
    }
    @Test
    public void test_isStudentEnrolled_false(){
        testCourse.addStudent("Jordan Lau");
        assertFalse(testCourse.isStudentEnrolled("Michael Cong"));
    }
}