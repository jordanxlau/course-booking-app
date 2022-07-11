package com.example.course_booking_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public abstract class CustomActivity extends AppCompatActivity {
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

    //Opens course add fragment
    protected void courseFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_right_to_left);
        ft.replace(R.id.courseFragment, new CourseAddFragment());
        ft.commit();
    }

    //Opens course instruct fragment
    protected void courseInstructFragment(){
        AddInstructorFragment fragment = new AddInstructorFragment();
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_right_to_left).add(R.id.coordinatorLayout, fragment).commit();
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

    @Override
    public void onBackPressed(){
        //disable the default back button.
    }

}
