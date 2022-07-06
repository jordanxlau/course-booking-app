package com.example.course_booking_app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;
import android.content.ContextWrapper;

/**
 * JUnit tests for {@Link DatabaseHandler}
 */
public class DatabaseHandlerTests {
    private DatabaseHandler testDB;
    @Before
    public void setUp(){
        testDB = new DatabaseHandler(MainActivity.context, "testDB.db", 22);
    }
    @Test
    public void testDB_is_working(){
        assertNotEquals(null, testDB);
    }
    @Test
    public void addUser_returns_correct_message() {
        int returnMessage = testDB.addUser("testUser","password","instructor");
        assertEquals(0, returnMessage);
    }
}