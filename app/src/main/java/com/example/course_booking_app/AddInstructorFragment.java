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
 *
 */
public class AddInstructorFragment extends Fragment {

    //Attribute declarations
    TextView textView, courseName, courseDescription;
    Button confirm, cancel;

    public AddInstructorFragment() {
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
        courseName = view.findViewById(R.id.courseName);
        courseDescription = view.findViewById(R.id.courseDescription);

        //Declare course currently in question
        Course course = InstructorActivity.courseToJoin;

        //Set TextViews
        courseName.setText(course.getName());
        courseDescription.setText("SET COURSE DESCRIPTION LATER");
        if (! course.getInstructor().equals(CustomActivity.currentUser)){//the current user is not the current instructor
            textView.setText("I want to instruct " + InstructorActivity.courseToJoin.getCode());
        } else {
            textView.setText("I no longer want to instruct " + InstructorActivity.courseToJoin.getCode());
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course newCourse;
                InstructorActivity.courseList.remove(course);
                MainActivity.db.removeCourse(course.getID());
                if (! course.getInstructor().equals(CustomActivity.currentUser)) {//the current user is not the current instructor
                    //Checks if course already has an instructor
                    if (! course.getInstructor().equals("")){
                        Toast.makeText(getActivity(), "Course Already Has Instructor", Toast.LENGTH_SHORT).show();
                        //Error message
                        InstructorActivity.courseList.add(course);
                        return;
                    }
                    //Else, proceed with adding the newCourse with the new instructor
                    newCourse = new Course(course.getID(), course.getCode(), course.getName(), CustomActivity.currentUser);
                    MainActivity.db.addCourse(course.getCode(), course.getName(), CustomActivity.currentUser);

                    //Success message
                    Toast.makeText(getActivity(), "You now teach " + newCourse.getCode(), Toast.LENGTH_SHORT).show();
                } else {//the current user IS the current instructor
                    //DELETE THE NEW COURSE'S DESCRIPTION AND STUDENT CAPACITY !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    newCourse = new Course(course.getID(), course.getCode(), course.getName(), "");
                    MainActivity.db.addCourse(course.getCode(), course.getName(), "");

                    //Success message
                    Toast.makeText(getActivity(), "You no longer teach " + newCourse.getCode(), Toast.LENGTH_SHORT).show();
                }
                InstructorActivity.courseList.add(newCourse);

                //this fragment will now remove itself
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_right_to_left).remove(AddInstructorFragment.this).commit();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
                //this fragment will now remove itself
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_right_to_left).remove(AddInstructorFragment.this).commit();
            }
        });

        return view;
    }
}