package com.example.course_booking_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseEnrollmentActivity extends CourseBookingAppActivity {

    protected Button search;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_enrollment);

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String desiredCode = searchCode.getText().toString();

                //Reset to the view of all courses
                if (desiredCode == null || desiredCode == ""){
                    courseRVAdapter = new CourseRVAdapter(courseList, CoursesActivity.this);
                    linearLayoutManager = new LinearLayoutManager(CoursesActivity.this, RecyclerView.VERTICAL, false);
                    coursesRV.setLayoutManager(linearLayoutManager);
                    new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(coursesRV);
                    coursesRV.setAdapter(courseRVAdapter);
                    return;
                }

                //Creates a list of the courses that match the searched course code
                sameCodeCourseList = new ArrayList<CourseModal>();
                for (CourseModal course: sameCodeCourseList){
                    if (course.getCode().equals(desiredCode)){
                        sameCodeCourseList.add(course);
                    }
                }

                //Sets the RV to the newly created list
                courseRVAdapter = new CourseRVAdapter(sameCodeCourseList, CoursesActivity.this);
                linearLayoutManager = new LinearLayoutManager(CoursesActivity.this, RecyclerView.VERTICAL, false);
                coursesRV.setLayoutManager(linearLayoutManager);
                new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(coursesRV);
                coursesRV.setAdapter(courseRVAdapter);
            }
        });
        /*search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String desiredName = searchName.getText().toString();

                //Reset to the view of all courses
                if (desiredName == null || desiredName == ""){
                    courseRVAdapter = new CourseRVAdapter(courseList, CoursesActivity.this);
                    return;
                }

                //Creates a list of the courses that match the searched course code
                sameNameCourseList = new ArrayList<CourseModal>();
                for (CourseModal course: sameNameCourseList){
                    if (course.getCode().equals(desiredName)){
                        sameNameCourseList.add(course);
                    }
                }

                //Sets the RV to the newly created list
                courseRVAdapter = new CourseRVAdapter(sameNameCourseList, CoursesActivity.this);
            }
        });*/
    }

}