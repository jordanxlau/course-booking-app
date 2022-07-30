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
        assertEquals("Jordan;Eric;Michael",s);
    }
    @Test
    public void test_stringToList(){
        ArrayList l = Utils.stringToList("Jordan;Eric;Michael");
        assertEquals(3, l.size());
        assertEquals("Jordan", l.get(0));
        assertEquals("Eric", l.get(1));
        assertEquals("Michael", l.get(2));
    }
}
