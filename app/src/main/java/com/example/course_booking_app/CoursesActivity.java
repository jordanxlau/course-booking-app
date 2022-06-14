package com.example.course_booking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

    //Declarations for toast
    public int duration = Toast.LENGTH_LONG;
    public static Toast toast;
    public Context context;

    //Other declarations
    private ArrayList<CourseModal> courseModalArrayList;
    private DatabaseHandler dbHandler;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        //Initialize widgets
        back = findViewById(R.id.back);
        viewUsers = findViewById(R.id.viewUsers);
        addCourse = findViewById(R.id.addCourse);

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
        };

        //Other setup
        courseModalArrayList = new ArrayList<>();
        dbHandler = new DatabaseHandler(CoursesActivity.this);

        courseModalArrayList = dbHandler.getCourses();

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