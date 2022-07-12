package com.example.course_booking_app;


public class ValidationResults{
    //main attributes
    private boolean validity;
    private String message;

    private boolean validDesc;
    private boolean validCap;
    private boolean validDays;
    private boolean validTimePeriod;

    //verification attributes
    /*
    private String description;
    private String capacity;
    private String classDays;
    private String classTimePeriod;
    */

    private static final int MAX_DESC_LENGTH = 500;
    private static final int MAX_COURSE_CAPACITY = 1000;

    //private static ValidationResults instance;


    //Empty constructor
    /*
    public ValidationResults() {
    	validity = false;
    	message = "";

    	validDesc = false;
    	validCap = false;
    	validDays = false;
    	validTimePeriod = false;
    }*/

    //Constructor with params. Validates automatically
    public ValidationResults(String desc, String cap, String days, String timePeriod) {
        validateFields(desc, cap, days, timePeriod);
        updateErrorMessage();

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


    public String getMessage() {
        return message;
    }

    public boolean getValidDesc() {
        return validDesc;
    }

    public boolean getValidCap() {
        return validCap;
    }

    public boolean getValidDays() {
        return validDays;
    }

    public boolean getValidTimePeriod() {
        return validTimePeriod;
    }

    /*
    public ValidationResults getInstance() {
        return this.instance;
    }*/

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

    public void updateErrorMessage() {
        StringBuilder stronk = new StringBuilder();
        if(!validDesc) {
            stronk.append(getDescError());
            stronk.append(" ");
        }
        if(!validCap) {
            stronk.append(getCapError());
            stronk.append(" ");
        }
        if(!validDays) {
            stronk.append(getDaysError());
            stronk.append(" ");
        }
        if(!validTimePeriod) {
            stronk.append(getTimePeriodError());
            stronk.append("");
        }

        message = stronk.toString();

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
            if(!checkTimeFormat(eachField)) {
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

    //check if String is in "HH:MM-HH:MM" format
    //hours must be between 00 and 24
    //minutes must be between 00 and 60
    private boolean checkTimeFormat(String s) {

        //s = s.strip();

        if(s == null) return false;
        if(s.length() != 11) return false;
        //char a = s.charAt(2);
        Character colon1 = s.charAt(2);
        Character colon2 = s.charAt(8);
        Character hyphen = s.charAt(5);

        //there must be colons at indices 2 and 8 in the String
        if(!colon1.equals(':')) return false;
        if(!colon2.equals(':')) return false;
        //hyphen at index 5
        if(!hyphen.equals('-')) return false;



        String hours1 = s.substring(0, 2);
        String hours2 = s.substring(6, 8);
        String minutes1 = s.substring(3, 5);
        String minutes2 = s.substring(9, 11);

        if(!isInteger(hours1)) return false;
        if(!isInteger(hours2)) return false;
        if(!isInteger(minutes1)) return false;
        if(!isInteger(minutes2)) return false;

        //check that 1st and 3rd number are between 00 and 24
        int h1 = Integer.parseInt(hours1);
        int h2 = Integer.parseInt(hours2);
        if(h1 < 0 || h1 > 24) {
            return false;
        }
        if(h2 < 0 || h2 > 24) {
            return false;
        }

        //check that 2nd and 4th are between 00 and 60
        int m1 = Integer.parseInt(minutes1);
        int m2 = Integer.parseInt(minutes2);
        if(m1 < 0 || m1 > 60) {
            return false;
        }
        if(m2 < 0 || m2 > 60) {
            return false;
        }


        //got here only if format is correct
        return true;
    }

    //check if String is integer
    private static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    private static boolean isInteger(String s, int radix) {
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

    //Error messages
    private static String getDescError() {
        return "Description must be between 1 and 500 characters.";
    }

    private static String getCapError() {
        String s = "Capacity must be an integer between 1 and " + MAX_COURSE_CAPACITY;
        return s;
    }

    private static String getDaysError() {
        String s = "Days must be valid days of a week , separated by commas (e.g. \"Monday, Wednesday\").";
        return s;
    }

    private static String getTimePeriodError() {
        String s = "Time period format: \"HH:MM-HH:MM; HH:MM-HH:MM\". Multiple periods must be separated by a semi-colon (i.e. ';').";
        return s;
    }

}
