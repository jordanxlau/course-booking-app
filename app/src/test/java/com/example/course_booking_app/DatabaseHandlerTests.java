package com.example.course_booking_app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DatabaseHandlerTests {
    DatabaseHandler testDB = new DatabaseHandler(MainActivity.context, "testDB.db", 22);
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void addUser_returns_correct_boolean() {
        assertTrue(testDB.addUser("testUser","password","instructor"));
    }
}