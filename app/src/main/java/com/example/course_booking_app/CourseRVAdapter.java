package com.example.course_booking_app;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<CourseModal> courseModalArrayList;
    private Context context;

    // constructor
    public CourseRVAdapter(ArrayList<CourseModal> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        CourseModal modal = courseModalArrayList.get(position);
        holder.courseIDTV.setText(modal.getID());
        holder.courseCodeTV.setText(modal.getCode());
        holder.courseNameTV.setText(modal.getName());
        holder.courseInstructorTV.setText(modal.getInstructor());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView courseIDTV, courseCodeTV, courseNameTV, courseInstructorTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            courseIDTV = itemView.findViewById(R.id.idTVcourseID);
            courseCodeTV = itemView.findViewById(R.id.idTVcourseCode);
            courseNameTV = itemView.findViewById(R.id.idTVcourseName);
            courseInstructorTV = itemView.findViewById(R.id.idTVcourseInstructor);

        }
    }

    public interface OnCourseListener{
        void onCourseClick(int position);
    }

}