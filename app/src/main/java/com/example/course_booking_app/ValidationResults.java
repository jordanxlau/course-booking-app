package com.example.course_booking_app;

import java.util.IllegalFormatException;

public class ValidationResults{
    private boolean validity;
    //private String message;
    private boolean validDesc;
    private boolean validCap;
    private boolean validDays;
    private boolean validTimePeriod;

    private static final int MAX_DESC_LENGTH = 500;
    private static final int MAX_COURSE_CAPACITY = 1000;

    private static ValidationResults instance;

    public ValidationResults(String desc, String cap, String days, String timePeriod) {
        validateFields(desc, cap, days, timePeriod);


    }

    /*
    private ValidationResults(boolean validity, String message) {
        this.validity = validity;
        this.message = message;
    }*/

    /*
    private ValidationResults() {
        this.validity = false;
        //this.message = "";

        validDesc = false;
        validCap = false;
        validDays = false;
        validTimePeriod = false;

        //automatically call validation upon creation?
    }*/

    public boolean getValidity() {
        return validity;
    }

    /*
    public String getMessage() {
        return message;
    }*/

    public ValidationResults getInstance() {
        return this.instance;
    }

    public void validateFields(String desc, String cap, String days, String timePeriod) {


        //validate description
        validDesc = validateDesc(desc);
        //validate capacity
        validCap = validateCap(cap);
        //validate days
        validDays = validateDays(days);
        //validate timePeriod
        validTimePeriod = validateTimePeriod(timePeriod);

        if(validDesc && validCap && validDays && validTimePeriod) {
            validity = true;
        }




        //instance params automatically modified.

    }


    //private helper methods below
    //Course code validation


    //Course name field validation


    //Course instructor field validation
    ///^^^ above 3 not needed


    //Course description validation (has to be non-empty string)
    private boolean validateDesc(String s) {
        if(s == null) return false;
        if(s.length() == 0) return false;
        if(s.length() > MAX_DESC_LENGTH) return false;

        return true;
    }

    //Course capacity field validation (positive int 1-10000)
    /*
    private boolean validateCap(int cap) {

        if(cap <= 0) return false;
        if(cap > MAX_COURSE_CAPACITY) return false;

        return true;
    }*/

    //Course capacity field validation (String is positive int range 1-10000)
    private boolean validateCap(String cap) {
        if(!isInteger(cap)) return false;
        int capacity = Integer.parseInt(cap);
        if(capacity <= 0) return false;
        if(capacity > MAX_COURSE_CAPACITY) return false;

        return true;
    }

    //Course length Days (of the week) validation
    private boolean validateDays(String s) {
        //basic check
        if(s == null) return false;
        if(s.length() == 0) return false;

        //parse s into String array delim. by comma
        String[] parsed = parseComma(s);
        //validate each index in the array
        for(String eachField : parsed) {
            if(!checkDayOfWeek(eachField)) {
                return false;
            }
        }

        //if all success, input is valid
        return true;
    }

    //Course time-period validation (format: XX:XX-XX:XX; XX:XX-XX:XX, 24h time format)
    private boolean validateTimePeriod(String s) {
        if(s == null) return false;
        if(s.length() == 0) return false;

        //parse s into String array delim. by semi-colons
        String[] parsed = parseSemicolon(s);
        //then for each field, validate format
        for(String eachField : parsed) {
            if(!checkDayOfWeek(eachField)) {
                return false;
            }

        }

        return true;
    }

    //String parse by comma, used in Days validation
    private String[] parseComma(String s) {
        //StringBuilder newString = new StringBuilder();
        String[] s1 = s.split(",");
        String[] s2 = new String[s1.length];

        //Trim start/end spaces
        for(int i= 0; i < s1.length; i++) {
            s2[i] = s1[i].trim();
        }

        return s2;
    }

    //String parse by semi-colon, used in time-period validation
    private String[] parseSemicolon(String s) {
        //StringBuilder newString = new StringBuilder();
        String[] s1 = s.split(";");
        String[] s2 = new String[s1.length];

        //Trim start/end spaces
        for(int i= 0; i < s1.length; i++) {
            s2[i] = s1[i].trim();
        }

        return s2;
    }

    //check if String represents a Day of a week
    private boolean checkDayOfWeek(String s) {
        if(s == null) return false;
        if(s.length() == 0) return false;

        boolean isValid = false;

        //each field s must exactly match one of the seven

        if(s.equals("Monday")) {
            isValid = true;
        } else if(s.equals("Monday")) {
            isValid = true;
        } else if(s.equals("Tuesday")) {
            isValid = true;
        } else if(s.equals("Wednesday")) {
            isValid = true;
        } else if(s.equals("Thursday")) {
            isValid = true;
        } else if(s.equals("Friday")) {
            isValid = true;
        } else if(s.equals("Saturday")) {
            isValid = true;
        } else if(s.equals("Sunday")) {
            isValid = true;
        }

        return isValid;
    }

    //check if String is in "XX:XX" format
    private boolean checkTimeFormat(String s) {
        if(s == null) return false;
        if(s.length() != 5) return false;
        //char a = s.charAt(2);

        if(s.indexOf(':') != 2) return false;
        //strip the colon, the 4 remainin characters must be integers
        if( !isInteger( s.replaceAll(":", "") ) ) return false;

        //got here only if format is correct
        return true;
    }

    //check if String is integer
    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }





}
