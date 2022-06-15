package com.example.course_booking_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class CourseAddFragment extends Fragment{

    private class ValidationResults{
        private boolean validity;
        private String message;

        private ValidationResults(boolean validity, String message) {
            this.validity = validity;
            this.message = message;
        }

        public boolean getValidity() {
            return validity;
        }

        public String getMessage() {
            return message;
        }
    }

    public CourseAddFragment() {
        // Required empty public constructor
    }

    protected EditText editCourseName, editCourseInstructor, editCourseCode;
    protected Button addCourse;

    public static CourseAddFragment newInstance() {
        CourseAddFragment fragment = new CourseAddFragment();
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
        View view = inflater.inflate(R.layout.fragment_course_add, container, false);

        //create the widgets
        editCourseName = (EditText) view.findViewById(R.id.editCourseName);
        editCourseCode = (EditText) view.findViewById(R.id.editCourseCode);
        editCourseInstructor = (EditText) view.findViewById(R.id.editInstructor);
        addCourse = (Button) view.findViewById(R.id.addCourse);

        if(((CoursesActivity)getActivity()).refreshStatus == RefreshStatus.EDITCOURSE){
            editCourseName.setText(((CoursesActivity)getActivity()).modifiedCourse.getName());
            editCourseCode.setText(((CoursesActivity)getActivity()).modifiedCourse.getCode());
            editCourseInstructor.setText(((CoursesActivity)getActivity()).modifiedCourse.getInstructor());
        }

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add the user
                String tempCode = editCourseCode.getText().toString();
                String tempName = editCourseName.getText().toString();
                String tempInstructor = editCourseInstructor.getText().toString();

                String toastMessage = "";

                ValidationResults results = validateFields(tempCode, tempName, tempInstructor);

                if(results.getValidity() == true){

                    ((CoursesActivity)getActivity()).modifiedCourse.setCode(tempCode.toUpperCase());
                    ((CoursesActivity)getActivity()).modifiedCourse.setName(tempName);
                    ((CoursesActivity)getActivity()).modifiedCourse.setInstructor(tempInstructor);

                    if(((CoursesActivity)getActivity()).refreshStatus == RefreshStatus.ADDCOURSE){
                        toastMessage = "Information saved. Press the refresh button to add course. ";
                    }
                    else if(((CoursesActivity)getActivity()).refreshStatus == RefreshStatus.EDITCOURSE){
                        toastMessage = "Information saved. Press the refresh button to confirm these changes. ";
                    }

                    Toast.makeText(getActivity(), toastMessage, Toast.LENGTH_SHORT).show();
                    //this fragment will now remove itself
                    getActivity().getSupportFragmentManager().beginTransaction().remove(CourseAddFragment.this).commit();
                }
                else{
                    toastMessage = results.getMessage();
                    String[] messageArray = toastMessage.split("\\.");

                    for(int i = 0; i < messageArray.length; i ++){
                        Toast.makeText(getActivity(), messageArray[i], Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        return view;
    }

    private ValidationResults validateFields(String tempCode, String tempName, String tempInstructor){

        boolean isValid = true;
        boolean correctCourseCode = true;
        String message = "";

        //validate course code
        if(tempCode.length() != 8){
            isValid = false;
            message = message + "Course code must be 8 characters in length.";
        }
        else{
            //first three characters must be letters
            for(int i = 0; i < 3; i ++){
                if(Character.isLetter(tempCode.charAt(i)) == false){
                    correctCourseCode = false;
                    break;
                }
            }
            //next four characters must be numbers
            for(int i = 3; i < 7; i ++){
                if(Character.isDigit(tempCode.charAt(i)) == false){
                    correctCourseCode = false;
                    break;
                }
            }
            //last character must be a letter
            if(Character.isLetter(tempCode.charAt(7)) == false){
                correctCourseCode = false;
            }

            if(correctCourseCode == false){
                isValid = false;
                message = message + "Course code must be in the format of 3 letters, 4 numbers, 1 letter.";
            }
        }

        //validate name field
        if(tempName.length() < 6){
            isValid = false;
            message = message + "The course name field must be at least 6 characters.";
        }
        else if(tempName.length() > 75){
            isValid = false;
            message = message + "The course name field must be at most 75 characters.";
        }

        //validate instructor field
        if(tempInstructor.length() < 6){
            isValid = false;
            message = message + "The course instructor field must be at least 6 characters.";
        }
        else if(tempInstructor.length() > 50){
            isValid = false;
            message = message + "The course instructor field must be at most 50 characters.";
        }

        return new ValidationResults(isValid, message);
    }

    private String format
}