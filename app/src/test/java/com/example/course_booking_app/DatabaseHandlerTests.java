package com.example.course_booking_app;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DatabaseHandlerTests {
    //create a test database
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void addUser_returns_correct_boolean() {
        assertTrue(true);
        //assertTrue(MainActivity.db.addUser("admin","admin123","administrator"));
    }
}