package com.example.course_booking_app;

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
    private ArrayList<Course> sameCodeCourseList, sameNameCourseList, courseList, myCourseList;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;
    private ItemClick onClick;

    //declaration for modified course
    public static Course modifiedCourse;
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

        //Initialize usernameDisplay
        usernameDisplay.setText("logged in as instructor: " + currentUser);

        //setup related to course information
        modifiedCourse = new Course("","","","");
        refreshStatus = RefreshStatus.NOCHANGE;

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

                //Creates a list of the courses that contain the searched course code
                sameCodeCourseList = new ArrayList<Course>();
                for (Course course: courseList){
                    if (course.getCode().toLowerCase().contains(desiredCode) && course.getName().toLowerCase().contains(desiredName))
                        sameCodeCourseList.add(course);
                }
                
                //Sets the RV to the newly created list
                courseRVAdapter = new CourseRVAdapter(sameCodeCourseList, InstructorActivity.this, InstructorActivity.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(InstructorActivity.this, RecyclerView.VERTICAL, false);
                coursesRV.setLayoutManager(linearLayoutManager);
                //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(coursesRV);
                coursesRV.setAdapter(courseRVAdapter);
            }
        });

        //Create context menu
        //registerForContextMenu(coursesRV);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this,"SUCCESSSSSSSSSS", duration);
    }

    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Assign yourself to this course?");
        getMenuInflater().inflate(R.menu.select_course_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.enroll:
                System.out.println("ASSIGN INSTRUCTOR TO COURSE");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onContextMenuClosed(@NonNull Menu menu) {
        super.onContextMenuClosed(menu);
    }*/
}