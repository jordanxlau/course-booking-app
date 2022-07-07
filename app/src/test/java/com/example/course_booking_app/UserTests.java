package com.example.course_booking_app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit tests for {@Link Course}
 */
public class UserTests  {
    private User testUser;

    @Before
    public void setUp(){
        testUser = new User("1234", "Jordan Lau", "password123","");
    }
    @Test
    public void test_getCode() {
        assertEquals("", .getCode());
    }

    @Test
    public void test_getInstructor(){
        assertEquals("","");
    }
    @Test
    public void test_admin_added(){
        assertEquals("","");
    }

}