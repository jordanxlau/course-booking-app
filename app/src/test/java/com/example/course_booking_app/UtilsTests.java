package com.example.course_booking_app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * JUnit tests for {@Link Course}
 */
public class UtilsTests {

    private ArrayList<String> testList;
    private User testUser1, testUser2;

    @Before
    public void setUp(){
        testList = new ArrayList<>();
    }

    @Test
    public void test_listToString(){
        testList.add("Jordan");
        testList.add("Eric");
        testList.add("Michael");
        String s = Utils.listToString(testList);
        assertEquals("",s);
    }
    @Test
    public void test_stringToList(){

    }
}
