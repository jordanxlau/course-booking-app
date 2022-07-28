package com.example.course_booking_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class JoinCourseFragment extends Fragment {

    //Attribute declarations
    TextView textView, courseName, courseDescription;
    Button confirm, cancel;


    public JoinCourseFragment() {
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
        View view = inflater.inflate(R.layout.fragment_join_course, container, false);



        //Attribute initializations
        confirm = view.findViewById(R.id.confirm);
        cancel = view.findViewById(R.id.cancel);
        textView = view.findViewById(R.id.textView);
        courseName = view.findViewById(R.id.courseName);
        courseDescription = view.findViewById(R.id.courseDescription);

        //Declare course currently in question
        Course course = StudentActivity.courseToJoin;

        //Set TextViews
        courseName.setText(course.getName());
        courseDescription.setText("SET COURSE DESCRIPTION LATER");
        if (true){//the current user is not enrolled in the course
            textView.setText("I want to enroll in " + StudentActivity.courseToJoin.getCode());
        } else {
            textView.setText("I no longer want to be enrolled in " + StudentActivity.courseToJoin.getCode());
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!course.isStudentEnrolled(CustomActivity.currentUser)) {//the current user is not in the class
                    course.addStudent(CustomActivity.currentUser);

                    //Success message
                    Toast.makeText(getActivity(), "You have enrolled in " + course.getCode(), Toast.LENGTH_SHORT).show();
                } else {//the current user IS in the class
                    course.removeStudent(CustomActivity.currentUser);
                    //Success message
                    Toast.makeText(getActivity(), "You are no longer enrolled in " + course.getCode(), Toast.LENGTH_SHORT).show();
                }

                //this fragment will now remove itself
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_right_to_left).remove(JoinCourseFragment.this).commit();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
                //this fragment will now remove itself
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_right_to_left).remove(JoinCourseFragment.this).commit();
            }
        });

        return view;
    }
}