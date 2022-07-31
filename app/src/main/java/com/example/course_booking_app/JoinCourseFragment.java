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
<<<<<<< HEAD
    TextView statusText, courseName, courseDescription;
=======
    TextView textView, courseName, courseDescription, courseDays, courseTimes;
>>>>>>> 1d4cee3baa36b5a8c3cda65cd873b4b145d0008b
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
        statusText = view.findViewById(R.id.statusText);
        courseName = view.findViewById(R.id.courseName);
        courseDescription = view.findViewById(R.id.courseDescription);
        courseDays = view.findViewById(R.id.courseDays);
        courseTimes = view.findViewById(R.id.courseTimes);

        //Declare course currently in question
        Course selectedCourse = ((StudentActivity)getActivity()).courseToJoin;

        //Set TextViews
<<<<<<< HEAD
        courseName.setText(selectedCourse.getName());
        courseDescription.setText(selectedCourse.getDescription());
        if (selectedCourse.isStudentEnrolled(((StudentActivity)getActivity()).currentUser)){//the current user is enrolled in the course
            statusText.setText("I want to enroll in " + selectedCourse.getCode());
=======
        courseName.setText(course.getName());
        courseDescription.setText(course.getDescription());
        courseDays.setText(course.getDays());
        courseTimes.setText(course.getHours());
        if (!course.isStudentEnrolled(CustomActivity.currentUser)){//the current user is not enrolled in the course
            textView.setText("I want to enroll in " + StudentActivity.courseToJoin.getCode());
>>>>>>> 1d4cee3baa36b5a8c3cda65cd873b4b145d0008b
        } else {
            statusText.setText("I no longer want to be enrolled in " + selectedCourse.getCode());
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course newCourse = selectedCourse;
                ((StudentActivity)getActivity()).courseList.remove(selectedCourse);
                MainActivity.db.removeCourse(selectedCourse.getID());

                if (!selectedCourse.isStudentEnrolled(((StudentActivity)getActivity()).currentUser)) {//the current user is not enrolled in the course
                    //TIME CONFLICT FEATURE
                    if (!   MainActivity.db.isAvailableAt( ((StudentActivity)getActivity()).currentUser, selectedCourse.getDays(), selectedCourse.getHours() )   ) {//If the user has a time conflict
                        MainActivity.db.addCourse(selectedCourse.getCode(), selectedCourse.getName(), selectedCourse.getInstructor(), selectedCourse.getDescription(), selectedCourse.getDays(), selectedCourse.getHours(), selectedCourse.getStudentList());

                        //Error message
                        Toast.makeText(getActivity(), "Failed. You have a time conflict.", Toast.LENGTH_SHORT).show();
                    } else { //Else, proceed and add the new course with the new student
                        newCourse.addStudent(((StudentActivity)getActivity()).currentUser);
                        MainActivity.db.addCourse(selectedCourse.getCode(), selectedCourse.getName(), selectedCourse.getInstructor(), selectedCourse.getDescription(), selectedCourse.getDays(), selectedCourse.getHours(), newCourse.getStudentList());
                        MainActivity.db.addCourseAt( ((StudentActivity)getActivity()).currentUser, selectedCourse.getDays(), selectedCourse.getHours() );

                        //Success message
                        Toast.makeText(getActivity(), "You have enrolled in " + selectedCourse.getCode(), Toast.LENGTH_SHORT).show();
                    }
                } else {//the current user IS in the class
                    //add the new course without the student
                    newCourse.removeStudent(((StudentActivity)getActivity()).currentUser);
                    MainActivity.db.addCourse(selectedCourse.getCode(), selectedCourse.getName(), selectedCourse.getInstructor(), selectedCourse.getDescription(), selectedCourse.getDays(), selectedCourse.getHours(), newCourse.getStudentList());
                    MainActivity.db.removeCourseAt( ((StudentActivity)getActivity()).currentUser, selectedCourse.getDays(), selectedCourse.getHours() );

                    //Success message
                    Toast.makeText(getActivity(), "You are no longer enrolled in " + selectedCourse.getCode(), Toast.LENGTH_SHORT).show();
                }

                ((StudentActivity)getActivity()).courseList.add(newCourse);

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