package com.example.course_booking_app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * JUnit tests for {@Link User}
 */
public class UserTests  {
    private User testUser;

    @Before
    public void setUp(){
        testUser = new User("1234", "Jordan Lau", "password123","instructor");
    }


    @Test
    public void test_getUsertype() {
        assertEquals("instructor", testUser.getUsertype());
    }
    @Test
    public void test_getId() {
        assertEquals("1234", testUser.getID());
    }
    @Test
    public void test_getUsername(){
        assertEquals("Jordan Lau",testUser.getUsername());
    }
    @Test
    public void test_getPassword(){
        assertEquals("password123",testUser.getPassword());
    }
    @Test
    public void test_setUsername() {
        testUser.setUsername("Eric Van de Lande");
        assertEquals("Eric Van de Lande", testUser.getUsername());
    }
    @Test
    public void test_setUserType(){
        testUser.setUsertype("student");
        assertEquals("student",testUser.getUsertype());}
    @Test
    public void test_setInstructor(){
        testUser.setPassword("password567");
        assertEquals("password567",testUser.getPassword());}
    @Test
    public void test_setID(){
        testUser.setID("5678");
        assertEquals("5678",testUser.getID());}

    @Test
    public void test_removeCourseAtBlock(){}
    @Test
    public void test_addCourseAtBlock(){}
    @Test
    public void test_isAvailableAtBlock(){}

}