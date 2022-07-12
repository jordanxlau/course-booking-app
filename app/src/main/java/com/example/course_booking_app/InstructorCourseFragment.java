package com.example.course_booking_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    protected EditText editDescription, editCapacity, editDays, editHours;
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
        editHours = view.findViewById(R.id.editHours);

        saveChanges = view.findViewById(R.id.saveChanges);
        close = view.findViewById(R.id.close);
        assign = view.findViewById(R.id.assign);

        //initialize the text for our widgets
        textCourseCode.setText(((InstructorActivity)getActivity()).modifiedCourse.getCode());
        textCourseName.setText(((InstructorActivity)getActivity()).modifiedCourse.getName());
        textCourseInstructor.setText(((InstructorActivity)getActivity()).modifiedCourse.getInstructor());

        editDescription.setText(((InstructorActivity)getActivity()).modifiedCourse.getDescription());
        editCapacity.setText(((InstructorActivity)getActivity()).modifiedCourse.getCapacity());
        editDays.setText(((InstructorActivity)getActivity()).modifiedCourse.getDays());
        editHours.setText(((InstructorActivity)getActivity()).modifiedCourse.getHours());

        if(((InstructorActivity)getActivity()).assignStatus == AssignStatus.ASSIGNABLE){
            assign.setText("Assign");
            assign.setVisibility(View.VISIBLE);
            saveChanges.setVisibility(View.GONE);
        }
        else if(((InstructorActivity)getActivity()).assignStatus == AssignStatus.UNASSIGNABLE){
            assign.setText("Unassign");
            assign.setVisibility(View.VISIBLE);
            saveChanges.setVisibility(View.VISIBLE);
        }
        else{
            assign.setVisibility(View.GONE);
            saveChanges.setVisibility(View.GONE);
        }

        close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_right_to_left).remove(InstructorCourseFragment.this).commit();
            }
        });

        saveChanges.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //temporary fields
                String tempDescription = editDescription.getText().toString().trim();
                String tempCapacity = editCapacity.getText().toString().trim();
                String tempDays = editDays.getText().toString().trim();
                String tempHours = editHours.getText().toString().trim();

                ValidationResults validationResults = new ValidationResults(tempDescription, tempCapacity, tempDays, tempHours);

                if(validationResults.getValidity() == true){
                    ((InstructorActivity)getActivity()).modifiedCourse.setDescription(tempDescription);
                    ((InstructorActivity)getActivity()).modifiedCourse.setCapacity(tempCapacity);
                    ((InstructorActivity)getActivity()).modifiedCourse.setDays(tempDays);
                    ((InstructorActivity)getActivity()).modifiedCourse.setHours(tempHours);
                    Toast.makeText(getActivity(), "Changes saved", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_right_to_left).remove(InstructorCourseFragment.this).commit();
                }
                else{
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage(validationResults.getMessage());
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });

        assign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(((InstructorActivity)getActivity()).assignStatus == AssignStatus.ASSIGNABLE){

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Confirm action");
                        builder.setMessage("Are you sure that you want to teach this course?");
                        builder.setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int which){
                                        Toast.makeText(getActivity(), "You have successfully assigned yourself.", Toast.LENGTH_SHORT).show();
                                        ((InstructorActivity)getActivity()).modifiedCourse.setInstructor(((InstructorActivity)getActivity()).currentUser);
                                        getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_right_to_left).remove(InstructorCourseFragment.this).commit();
                                    }
                                });
                        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                //do nothing
                            }
                        });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if(((InstructorActivity)getActivity()).assignStatus == AssignStatus.UNASSIGNABLE){
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                    builder2.setCancelable(true);
                    builder2.setTitle("Confirm action");
                    builder2.setMessage("Are you sure that you no longer want to teach this course?");
                    builder2.setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog2, int which){
                                    Toast.makeText(getActivity(), "You have successfully unassigned yourself.", Toast.LENGTH_SHORT).show();
                                    ((InstructorActivity)getActivity()).modifiedCourse.resetCourse();
                                    getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_right_to_left).remove(InstructorCourseFragment.this).commit();
                                }
                            });
                    builder2.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog2, int which){
                            //do nothing
                        }
                    });

                    AlertDialog dialog2 = builder2.create();
                    dialog2.show();
                }
            }
        });

        return view;
    }


}