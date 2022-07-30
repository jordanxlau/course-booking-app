package com.example.course_booking_app;

import java.util.ArrayList;

public abstract class Utils {

    //HELPER METHODS (used in DatabaseHandler)

    //converts an array list of Strings (the students names) to a single string to be stored in the database
    public static String listToString(ArrayList<String> list){
        String string = "";

        for (String item: list)
            string = string + ";" + item;

        return string;
    }

    //converts a String representation of an array list (of students names) back to an actual array list
    public static ArrayList<String> stringToList(String string){
        ArrayList<String> list = new ArrayList<>();
        String[] split = string.split(";");

        for (String item: split)
            list.add(item);

        list.remove(0);//the first item is a semicolon because of how the String representation was made

        return list;
    }

}
