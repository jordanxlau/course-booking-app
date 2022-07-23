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
    protected EditText searchCode, searchName;
    protected Button back, delete, search, myCourses;
    protected TextView usernameDisplay;
    protected boolean doublePressed = false; //this boolean confirms whether the user has confirmed their account deletion by double pressing delete

    public static Course courseToJoin;
    private ArrayList<Course> searchedCourseList, myCourseList;
    public static ArrayList<Course> courseList;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;
    private ItemClick onClick;


    //declaration for modified course
    public static Course modifiedCourse;
    public static RefreshStatus refreshStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        //Initialize widgets
        back = findViewById(R.id.back);
        //delete = findViewById(R.id.viewCourses);
        usernameDisplay = findViewById(R.id.usernameDisplay);

        //Initialize usernameDisplay
        usernameDisplay.setText("logged in as student: " + currentUser);

        search = findViewById(R.id.search);
        searchCode = findViewById(R.id.searchCode);
        searchName = findViewById(R.id.searchName);
        coursesRV = findViewById(R.id.recyclerView);
        myCourses = findViewById(R.id.viewUsers);

        //setup related to course information
        modifiedCourse = new Course("","","","");
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

                //Reset to the view of all courses
                if (desiredCode == "" && desiredName == ""){
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
                    if (course.getCode().toLowerCase().contains(desiredCode) && course.getName().toLowerCase().contains(desiredName))
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
                    //if (Student is in the class)
                        //myCourseList.add(course);
                }

                //Sets the RV to the newly created list
                courseRVAdapter = new CourseRVAdapter(myCourseList, StudentActivity.this, StudentActivity.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StudentActivity.this, RecyclerView.VERTICAL, false);
                coursesRV.setLayoutManager(linearLayoutManager);
                //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(coursesRV);
                coursesRV.setAdapter(courseRVAdapter);
            }
        });

        /*delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (doublePressed == true) {
                    toast = Toast.makeText(context, "Account deleted.", duration);
                    toast.show();
                    (MainActivity.db).removeUser(currentUser);
                    doublePressed = false;
                    openMain();
                } else {
                    toast = Toast.makeText(context, "Press again to confirm.", duration);
                    toast.show();
                    doublePressed = true;
                }
            }
        });*/

    }

    @Override
    public void onItemClick(int position) {
        courseToJoin = courseList.get(position);
        courseInstructFragment();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }




}

