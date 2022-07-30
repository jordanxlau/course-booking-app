package com.example.course_booking_app;

import java.util.ArrayList;

public abstract class Utils {

    //HELPER METHODS (used in DatabaseHandler)
    public static String listToString(ArrayList<String> list){
        String string = "";

        for (String item: list)
            string = string + ";" + item;

        return string;
    }

    public static ArrayList<String> stringToList(String string){
        ArrayList<String> list = new ArrayList<>();
        String[] split = string.split(";");

        for (String item: split)
            list.add(item);

        return list;
    }

}
