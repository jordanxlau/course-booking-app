package com.example.course_booking_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CoursesActivity extends CustomActivity implements ItemClick{

    //Attribute Declarations
    protected Button back, viewUsers, addCourse, refreshPage;

    //Other declarations
    private ArrayList<Course> courseList;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;
    private LinearLayoutManager linearLayoutManager;

    //declaration for modified course
    public static Course modifiedCourse;
    public static RefreshStatus refreshStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        //Initialize attributes
        back = findViewById(R.id.back);
        viewUsers = findViewById(R.id.myCourses);
        addCourse = findViewById(R.id.addCourse);
        refreshPage = findViewById(R.id.refreshPage);
        coursesRV = findViewById(R.id.recyclerView);

        //Item Touch Helper setup
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            //Deletes a course
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.RIGHT){
                    int pos;
                    String purgedCourse;
                    String courseCode;
                    pos = viewHolder.getAdapterPosition();
                    purgedCourse = courseList.get(pos).getID();
                    courseCode = courseList.get(pos).getCode();

                    toast = Toast.makeText(context, "Course '" + courseCode + "' deleted!", duration);
                    toast.show();

                    MainActivity.db.removeCourse(purgedCourse);
                    courseList.remove(pos);
                    courseRVAdapter.notifyDataSetChanged();
                }
                else if(direction == ItemTouchHelper.LEFT){
                    int pos;
                    pos = viewHolder.getAdapterPosition();
                    modifiedCourse = courseList.get(pos);
                    refreshStatus = RefreshStatus.EDITCOURSE;

                    courseFragment();
                }
            }
        };

        //setup related to course information
        modifiedCourse = new Course("","","","");
        refreshStatus = RefreshStatus.NOCHANGE;

        courseList = new ArrayList<>();
        courseList = MainActivity.db.getCourses();

        courseRVAdapter = new CourseRVAdapter(courseList, CoursesActivity.this, CoursesActivity.this);
        linearLayoutManager = new LinearLayoutManager(CoursesActivity.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(coursesRV);
        coursesRV.setAdapter(courseRVAdapter);

        //Create action listeners
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMain();
            }
        });

        viewUsers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openUsers();
            }
        });

        addCourse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                modifiedCourse = new Course("","","","");
                refreshStatus = RefreshStatus.ADDCOURSE;
                courseFragment();
            }
        });

        refreshPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String toastMessage = "Message";

                if(refreshStatus == RefreshStatus.NOCHANGE){
                    toastMessage = "There are no changes to be made. ";
                }
                else if(refreshStatus == RefreshStatus.ADDCOURSE){
                    if ( MainActivity.db.addCourse(modifiedCourse.getCode(), modifiedCourse.getName(), modifiedCourse.getInstructor()) == 0)
                        toastMessage = "The course has been added successfully. ";
                    else
                        toastMessage = "course already exists!";
                }
                else if(refreshStatus == RefreshStatus.EDITCOURSE){
                    MainActivity.db.modifyCourse(modifiedCourse);
                    toastMessage = "The course has been edited successfully. ";
                }

                toast = Toast.makeText(context, toastMessage, duration);
                toast.show();

                if(refreshStatus != RefreshStatus.NOCHANGE) {
                    refreshStatus = RefreshStatus.NOCHANGE;
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        //Does nothing
    }
}