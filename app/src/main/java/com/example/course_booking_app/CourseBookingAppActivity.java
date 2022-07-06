package com.example.course_booking_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public abstract class CourseBookingAppActivity extends AppCompatActivity {
    //Toast fields
    public static int duration = Toast.LENGTH_SHORT;
    public static Toast toast;
    public static Context context;

    //"Current" user info
    public static String currentUser = "";
    public static String currentType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        //Preset code
        super.onCreate(savedInstanceState);
        //Initialize context
        context = getApplicationContext();
    }

    //Opens admin welcome page
    protected void openAdministratorActivity(){
        Intent intent = new Intent(this, AdministratorActivity.class);
        startActivity(intent);
    }

    //Opens instructor welcome page
    protected void openInstructorActivity(){
        Intent intent = new Intent(this, InstructorActivity.class);
        startActivity(intent);
    }

    //Opens student welcome page
    protected void openStudentActivity(){
        Intent intent = new Intent(this, StudentActivity.class);
        startActivity(intent);
    }

    //Opens course fragment
    protected void courseFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.courseFragment, new CourseAddFragment());
        ft.commit();
    }

    //Opens main page
    protected void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Opens user view page
    protected void openUsers(){
        Intent intent = new Intent(this, AdministratorActivity.class);
        startActivity(intent);
    }

    //Opens course view page
    protected void openCourses(){
        Intent intent = new Intent(this, CoursesActivity.class);
        startActivity(intent);
    }

    //Opens enroll courses view page
    protected void openEnroll(){
        Intent intent = new Intent(this, CourseEnrollmentActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        //disable the default back button.
    }

}
