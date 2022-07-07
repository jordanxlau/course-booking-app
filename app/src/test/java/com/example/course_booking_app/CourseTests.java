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
}