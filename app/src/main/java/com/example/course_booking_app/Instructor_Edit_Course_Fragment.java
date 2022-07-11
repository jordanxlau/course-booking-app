package com.example.course_booking_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Instructor_Edit_Course_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Instructor_Edit_Course_Fragment extends Fragment {

    protected Button setCourseInfo;
    protected EditText courseHours, courseDays, courseDescription;


    public Instructor_Edit_Course_Fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Instructor_Edit_Course_Fragment newInstance() {
        Instructor_Edit_Course_Fragment fragment = new Instructor_Edit_Course_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_instructor__edit__course_, container, false);
        Button set = view.findViewById(R.id.setCourseInfo);
        EditText courseHours = view.findViewById(R.id.courseHoursBox);
        EditText courseDays = view.findViewById(R.id.courseDaysBox);
        EditText courseDescription = view.findViewById(R.id.descriptionBox);
        Course course = InstructorActivity.courseToJoin;

        setCourseInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course.setHours(courseHours.getText().toString().trim());
                course.setDescription(courseDescription.getText().toString().trim());
                course.setDays(courseDays.getText().toString().trim());

                Toast.makeText(getActivity(), "Course Info updated", Toast.LENGTH_SHORT).show();
                //this fragment will now remove itself
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_right_to_left).remove(Instructor_Edit_Course_Fragment.this).commit();
            }
        });








        return view;
    }
}