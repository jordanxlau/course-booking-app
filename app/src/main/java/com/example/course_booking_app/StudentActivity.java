package com.example.course_booking_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentActivity extends CustomActivity implements ItemClick {

    //Attribute Declarations
    protected Button back, search, myCourses;
    protected TextView usernameDisplay;
    protected EditText searchCode, searchName, searchDays;

    private ArrayList<Course> searchedCourseList, myCourseList;
    public static ArrayList<Course> courseList;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    //For joining a course
    public static Course courseToJoin;

    public static RefreshStatus refreshStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        //Initialize widgets
        back = findViewById(R.id.back);
        usernameDisplay = findViewById(R.id.usernameDisplay);

        //Initialize usernameDisplay
        usernameDisplay.setText("logged in as student: " + currentUser);

        search = findViewById(R.id.search);
        searchCode = findViewById(R.id.searchCode);
        searchName = findViewById(R.id.searchName);
        searchDays = findViewById(R.id.searchDays);
        coursesRV = findViewById(R.id.recyclerView);
        myCourses = findViewById(R.id.viewUsers);

        //setup related to course information
        refreshStatus = RefreshStatus.NOCHANGE;

        courseList = new ArrayList<>();
        courseList = MainActivity.db.getCourses();

        courseRVAdapter = new CourseRVAdapter(courseList, StudentActivity.this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StudentActivity.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);
        //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(coursesRV);
        coursesRV.setAdapter(courseRVAdapter);

        //Create action listeners
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Goes back to MainActivity.java
                openMain();
            }
        });

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Setup
                String desiredCode = searchCode.getText().toString().toLowerCase();
                if (desiredCode == null)
                    desiredCode = "";
                String desiredName = searchName.getText().toString().toLowerCase();
                if (desiredName == null)
                    desiredName = "";
                String desiredDay = searchDays.getText().toString().toLowerCase();
                if (desiredDay == null)
                    desiredDay = "";


                //Reset to the view of all courses
                if (desiredCode == "" && desiredName == "" && desiredDay == ""){
                    courseRVAdapter = new CourseRVAdapter(courseList, StudentActivity.this, StudentActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StudentActivity.this, RecyclerView.VERTICAL, false);
                    coursesRV.setLayoutManager(linearLayoutManager);
                    //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(coursesRV);
                    coursesRV.setAdapter(courseRVAdapter);
                    return;
                }

                //Creates a list of the courses that contain the searched course code
                searchedCourseList = new ArrayList<Course>();
                for (Course course: courseList){
                    if (course.getCode().toLowerCase().contains(desiredCode) && course.getName().toLowerCase().contains(desiredName) && course.getDays().toLowerCase().contains(desiredDay))
                        searchedCourseList.add(course);
                }

                //Sets the RV to the newly created list
                courseRVAdapter = new CourseRVAdapter(searchedCourseList, StudentActivity.this, StudentActivity.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StudentActivity.this, RecyclerView.VERTICAL, false);
                coursesRV.setLayoutManager(linearLayoutManager);
                //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(coursesRV);
                coursesRV.setAdapter(courseRVAdapter);
            }
        });

        myCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creates a list of the courses that are instructed by the current user
                myCourseList = new ArrayList<Course>();
                for (Course course: courseList){
                    if (course.isStudentEnrolled(currentUser))
                        myCourseList.add(course);
                }

                //Sets the RV to the newly created list
                courseRVAdapter = new CourseRVAdapter(myCourseList, StudentActivity.this, StudentActivity.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StudentActivity.this, RecyclerView.VERTICAL, false);
                coursesRV.setLayoutManager(linearLayoutManager);
                //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(coursesRV);
                coursesRV.setAdapter(courseRVAdapter);
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        courseToJoin = courseList.get(position);
        if (! courseToJoin.getDescription().equals(""))//the course info has been created (the description is no longer just "")
            joinCourseFragment();
        else
            Toast.makeText(context, "Course does not have dates and times yet.", Toast.LENGTH_SHORT).show();
    }

}
