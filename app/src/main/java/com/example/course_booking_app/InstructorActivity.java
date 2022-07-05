package com.example.course_booking_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InstructorActivity extends CourseBookingAppActivity{

    //Attribute Declarations
    protected Button back, viewCourses;
    protected TextView usernameDisplay;

    //Declarations for toast
    public int duration = Toast.LENGTH_LONG;
    public static Toast toast;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        //Initialize widgets
        back = findViewById(R.id.back);
        viewCourses = findViewById(R.id.viewCourses);
        usernameDisplay = findViewById(R.id.usernameDisplay);

        //Initialize context
        context = getApplicationContext();

        //Initialize usernameDisplay
        usernameDisplay.setText("logged in as: " + MainActivity.currentUser);

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
                openCourses();
            }
        });

    }
}