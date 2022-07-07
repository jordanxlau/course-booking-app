package com.example.course_booking_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class CourseInstructFragment extends Fragment {

    //Attribute declarations
    TextView textView;
    Button confirm, cancel;

    public CourseInstructFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_instruct, container, false);

        //Attribute initializations
        confirm = view.findViewById(R.id.confirm);
        cancel = view.findViewById(R.id.cancel);
        textView = view.findViewById(R.id.textView);

        //Declare course currently in question
        Course course = InstructorActivity.courseToJoin;
        if (! course.getInstructor().equals(CustomActivity.currentUser)){//the current user is not the current instructor
            textView.setText("I want to instruct " + InstructorActivity.courseToJoin.getCode());
        } else {
            textView.setText("I no longer want to instruct " + InstructorActivity.courseToJoin.getCode());
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstructorActivity.courseList.remove(course);
                MainActivity.db.removeCourse(course.getID());
                Course newCourse = new Course(course.getID(), course.getCode(), course.getName(), CustomActivity.currentUser);
                InstructorActivity.courseList.add(newCourse);

                Toast.makeText(getActivity(), "You now teach " + newCourse.getCode(), Toast.LENGTH_SHORT).show();
                return;
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
                return;
            }
        });

        return view;
    }
}