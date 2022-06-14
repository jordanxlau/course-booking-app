package com.example.course_booking_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseAddFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CourseAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseAddFragment.
     */
    // TODO: Rename and change types and number of parameters

    protected EditText editCourseName, editCourseInstructor, editCourseCode;
    protected Button addCourse;
    private CourseModal modifiedCourse;

    public static CourseAddFragment newInstance(String param1, String param2) {
        CourseAddFragment fragment = new CourseAddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_add, container, false);

        editCourseName = (EditText) view.findViewById(R.id.editCourseName);
        editCourseCode = (EditText) view.findViewById(R.id.editCourseCode);
        editCourseInstructor = (EditText) view.findViewById(R.id.editInstructor);
        addCourse = (Button) view.findViewById(R.id.addCourse);

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifiedCourse = new CourseModal("",
                        editCourseCode.getText().toString(),
                        editCourseName.getText().toString(),
                        editCourseInstructor.getText().toString()
                );
            }
        });

        return view;
    }

    public CourseModal getModifiedCourse(){
        return this.modifiedCourse;
    }

}