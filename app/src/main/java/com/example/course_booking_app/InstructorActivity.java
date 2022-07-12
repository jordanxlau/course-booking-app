package com.example.course_booking_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InstructorActivity extends CustomActivity implements ItemClick{

    //Attribute Declarations
    protected Button back, myCourses, search;
    protected TextView usernameDisplay;
    protected EditText searchCode, searchName;

    //Other declarations
    private ArrayList<Course> searchedCourseList, myCourseList;
    public static ArrayList<Course> courseList;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;
    private ItemClick onClick;

    //declaration for modified course
    public static Course modifiedCourse;

    //status for assigning/unassigning oneself to/from a course.
    public static AssignStatus assignStatus;

    //status for editing course
    public static RefreshStatus refreshStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        //Initialize attributes
        back = findViewById(R.id.back);
        myCourses = findViewById(R.id.myCourses);
        usernameDisplay = findViewById(R.id.usernameDisplay);
        search = findViewById(R.id.search);
        searchCode = findViewById(R.id.searchCode);
        searchName = findViewById(R.id.searchName);
        coursesRV = findViewById(R.id.recyclerView);

        //initialize statuses
        refreshStatus = RefreshStatus.NOCHANGE;
        assignStatus = AssignStatus.NOTALLOWED;

        //Initialize usernameDisplay
        usernameDisplay.setText("logged in as instructor: " + currentUser);

        //setup related to course information
        modifiedCourse = new Course("","","","");

        courseList = new ArrayList<>();
        courseList = MainActivity.db.getCourses();

        courseRVAdapter = new CourseRVAdapter(courseList, InstructorActivity.this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(InstructorActivity.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);
        //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(coursesRV);
        coursesRV.setAdapter(courseRVAdapter);

        //Create action listeners
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMain();
            }
        });

        //Item Touch Helper setup
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            //Deletes a course
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.LEFT){
                    int pos;
                    pos = viewHolder.getAdapterPosition();
                    modifiedCourse = courseList.get(pos);
                    InstructorCourseFragment();
                }
            }
        };

        myCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creates a list of the courses that are instructed by the current user
                myCourseList = new ArrayList<Course>();
                for (Course course: courseList){
                    if (course.getInstructor().equals(currentUser))
                        myCourseList.add(course);
                }

                //Sets the RV to the newly created list
                courseRVAdapter = new CourseRVAdapter(myCourseList, InstructorActivity.this, InstructorActivity.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(InstructorActivity.this, RecyclerView.VERTICAL, false);
                coursesRV.setLayoutManager(linearLayoutManager);
                //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(coursesRV);
                coursesRV.setAdapter(courseRVAdapter);
            }
        });

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(refreshStatus == RefreshStatus.EDITCOURSE){
                    refreshStatus = RefreshStatus.NOCHANGE;
                    MainActivity.db.modifyCourse(modifiedCourse);
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }

                //Setup
                String desiredCode = searchCode.getText().toString().toLowerCase();
                if (desiredCode == null)
                    desiredCode = "";
                String desiredName = searchName.getText().toString().toLowerCase();
                if (desiredName == null)
                    desiredName = "";

                //Reset to the view of all courses
                if (desiredCode == "" && desiredName == ""){
                    courseRVAdapter = new CourseRVAdapter(courseList, InstructorActivity.this, InstructorActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(InstructorActivity.this, RecyclerView.VERTICAL, false);
                    coursesRV.setLayoutManager(linearLayoutManager);
                    //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(coursesRV);
                    coursesRV.setAdapter(courseRVAdapter);
                    return;
                }
                else{
                    //Creates a list of the courses that contain the searched course code
                    searchedCourseList = new ArrayList<Course>();
                    for (Course course: courseList){
                        if (course.getCode().toLowerCase().contains(desiredCode) && course.getName().toLowerCase().contains(desiredName))
                            searchedCourseList.add(course);
                    }

                    //Sets the RV to the newly created list
                    courseRVAdapter = new CourseRVAdapter(searchedCourseList, InstructorActivity.this, InstructorActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(InstructorActivity.this, RecyclerView.VERTICAL, false);
                    coursesRV.setLayoutManager(linearLayoutManager);
                    //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(coursesRV);
                    coursesRV.setAdapter(courseRVAdapter);
                }


            }
        });

    }

    @Override
    public void onItemClick(int position) {
        modifiedCourse = courseList.get(position);

        if(modifiedCourse.getInstructor().equals(currentUser)){
            assignStatus = AssignStatus.UNASSIGNABLE;
        }
        else if(modifiedCourse.getInstructor().isEmpty()){
            assignStatus = AssignStatus.ASSIGNABLE;
        }
        else{
            assignStatus = AssignStatus.NOTALLOWED;
        }
        InstructorCourseFragment();
    }

}