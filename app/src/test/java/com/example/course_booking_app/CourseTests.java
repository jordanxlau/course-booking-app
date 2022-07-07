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
        testCourse = new Course("1234", "ITI1121B", "","");
    }
    @Test
    public void test_getCode() {
        assertEquals("", testCourse.getCode());
    }

    @Test
    public void test_getInstructor(){

        assertEquals("","");
    }

}