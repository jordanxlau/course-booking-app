package com.example.course_booking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity{

    //Widget Declarations
    protected Button back;
    protected Button viewUsers;
    protected Button addCourse;
    protected Button refreshPage;

    //Declarations for toast
    private int duration = Toast.LENGTH_LONG;
    private int shortDuration = Toast.LENGTH_SHORT;
    private static Toast toast;
    private Context context;

    //Other declarations
    private ArrayList<CourseModal> courseModalArrayList;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    //declaration for modified course
    public static CourseModal modifiedCourse = new CourseModal("","","","");
    public static RefreshStatus refreshStatus = RefreshStatus.NOCHANGE;

    @Override
    public void onBackPressed(){
        //do nothing. we disable the back button.
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        //Initialize widgets
        back = findViewById(R.id.back);
        viewUsers = findViewById(R.id.viewUsers);
        addCourse = findViewById(R.id.addCourse);
        refreshPage = findViewById(R.id.refreshPage);

        //Initialize context
        context = getApplicationContext();

        //Item Touch Helper setup
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                if(direction == ItemTouchHelper.RIGHT){
                    int pos;
                    String purgedCourse;
                    String courseCode;
                    pos = viewHolder.getAdapterPosition();
                    purgedCourse = courseModalArrayList.get(pos).getID();
                    courseCode = courseModalArrayList.get(pos).getCode();

                    toast = Toast.makeText(context, "Course '" + courseCode + "' deleted!", duration);
                    toast.show();
                    MainActivity.db.removeCourse(purgedCourse);
                    courseModalArrayList.remove(pos);
                    courseRVAdapter.notifyDataSetChanged();
                }
                else if(direction == ItemTouchHelper.LEFT){
                    int pos;
                    pos = viewHolder.getAdapterPosition();
                    modifiedCourse = courseModalArrayList.get(pos);
                    refreshStatus = RefreshStatus.EDITCOURSE;

                    courseFragment();
                }
            }
        };

        //Other setup
        courseModalArrayList = new ArrayList<>();

        courseModalArrayList = MainActivity.db.getCourses();

        courseRVAdapter = new CourseRVAdapter(courseModalArrayList, CoursesActivity.this);
        coursesRV = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CoursesActivity.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(coursesRV);
        coursesRV.setAdapter(courseRVAdapter);

        //Create action listeners
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Goes back to MainActivity.java
                openMain();
            }
        });

        viewUsers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Goes back to MainActivity.java
                openUsers();
            }
        });

        addCourse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                modifiedCourse = new CourseModal("","","","");
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
                    toastMessage = "The course has been added successfully. ";
                }
                else if(refreshStatus == RefreshStatus.EDITCOURSE){
                    toastMessage = "The course has been edited successfully. ";
                }

                refreshStatus = RefreshStatus.NOCHANGE;
                toast = Toast.makeText(context, toastMessage, duration);
                toast.show();

                if(refreshStatus != RefreshStatus.NOCHANGE) {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }
        });
    }

    protected void courseFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.courseFragment, new CourseAddFragment());
        ft.commit();
    }

    //Re-opens main page
    protected void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    protected void openUsers(){
        Intent intent = new Intent(this, AdministratorActivity.class);
        startActivity(intent);
    }

}