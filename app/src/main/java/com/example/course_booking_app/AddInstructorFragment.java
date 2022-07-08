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
 * Use the {@link AddInstructorFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class AddInstructorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //Attribute declarations
    TextView textView;
    Button confirm, cancel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_course_instruct.
     */
    // TODO: Rename and change types and number of parameters
    public static AddInstructorFragment newInstance(String param1, String param2) {
        AddInstructorFragment fragment = new AddInstructorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AddInstructorFragment() {
        // Required empty public constructor
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
                Course newCourse;
                InstructorActivity.courseList.remove(course);
                MainActivity.db.removeCourse(course.getID());
                if (! course.getInstructor().equals(CustomActivity.currentUser)) {//the current user is not the current instructor
                    newCourse = new Course(course.getID(), course.getCode(), course.getName(), CustomActivity.currentUser);
                    Toast.makeText(getActivity(), "You now teach " + newCourse.getCode(), Toast.LENGTH_SHORT).show();
                } else {//the current user IS the current instructor
                    newCourse = new Course(course.getID(), course.getCode(), course.getName(), "");
                    Toast.makeText(getActivity(), "You no longer teach " + newCourse.getCode(), Toast.LENGTH_SHORT).show();
                }
                InstructorActivity.courseList.add(newCourse);

                //this fragment will now remove itself
                getActivity().getSupportFragmentManager().beginTransaction().remove(AddInstructorFragment.this).commit();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
                //this fragment will now remove itself
                getActivity().getSupportFragmentManager().beginTransaction().remove(AddInstructorFragment.this).commit();
            }
        });

        return view;
    }
}