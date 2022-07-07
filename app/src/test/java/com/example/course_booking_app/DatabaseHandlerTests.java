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
        testDB = new DatabaseHandler(CustomActivity.context, "testDB1.db", 1);
    }
    @Test
    public void addUser_returns_correct_message() {
        int returnMessage = testDB.addUser("testUser","password","instructor");
        assertEquals(0, returnMessage);
    }
    @Test
    public void test_addtion(){
        assertEquals(2+2, 4);
    }
    @Test
    public void testDB_is_working(){
        assertEquals(null, testDB.getReadableDatabase());
    }
    @After
    public void tearDown(){
        testDB.close();
    }

}