package com.example.course_booking_app;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InstructorCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InstructorCourseFragment extends Fragment {

    public InstructorCourseFragment() {
        // Required empty public constructor
    }

    protected TextView textCourseCode, textCourseName, textCourseInstructor;
    protected EditText editDescription, editCapacity, editDays, editTimes;
    protected Button saveChanges, close, assign;

    public static InstructorCourseFragment newInstance(String param1, String param2) {
        InstructorCourseFragment fragment = new InstructorCourseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_instructor_course, container, false);

        //create the widgets
        textCourseCode = view.findViewById(R.id.textCourseCode);
        textCourseName = view.findViewById(R.id.textCourseName);
        textCourseInstructor = view.findViewById(R.id.textCourseInstructor);

        editDescription = view.findViewById(R.id.editDescription);
        editCapacity = view.findViewById(R.id.editCapacity);
        editDays = view.findViewById(R.id.editDays);
        editTimes = view.findViewById(R.id.editTimes);

        saveChanges = view.findViewById(R.id.saveChanges);
        close = view.findViewById(R.id.close);
        assign = view.findViewById(R.id.assign);

        //initialize the text for our widgets



        return view;
    }
}