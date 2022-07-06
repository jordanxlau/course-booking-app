package com.example.course_booking_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InstructorActivity extends CourseBookingAppActivity{

    //Attribute Declarations
    protected Button back, viewCourses;
    protected TextView usernameDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        //Initialize attributes
        back = findViewById(R.id.back);
        viewCourses = findViewById(R.id.viewCourses);
        usernameDisplay = findViewById(R.id.usernameDisplay);

        //Initialize usernameDisplay
        usernameDisplay.setText("logged in as: " + currentUser);

        //Create action listeners
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Goes back to MainActivity.java
                openMain();
            }
        });

        viewCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnroll();
            }
        });

    }
}