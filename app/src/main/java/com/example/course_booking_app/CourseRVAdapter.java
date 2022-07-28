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

    // field declarations
    private ArrayList<Course> courseArrayList;
    private Context context;
    
    //for the onClick handler
    private final ItemClick itemClick;

    //for the context menu
    private int position;

    //getter
    public int getPosition() {
        return position;
    }

    //setter
    public void setPosition(int position) {
        this.position = position;
    }

    // constructor
    //public CourseRVAdapter(ArrayList<Course> courseArrayList, Context context) {
    //    this.courseArrayList = courseArrayList;
    //    this.context = context;
    //}
    
    //second constructor (overloading)
    public CourseRVAdapter(ArrayList<Course> courseArrayList, Context context, ItemClick itemClick) {
        this.courseArrayList = courseArrayList;
        this.context = context;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_item, parent, false);
        return new ViewHolder(view, itemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        Course modal = courseArrayList.get(position);
        holder.courseIDTV.setText(modal.getID());
        holder.courseCodeTV.setText(modal.getCode());
        holder.courseNameTV.setText(modal.getName());
        holder.courseInstructorTV.setText(modal.getInstructor());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        if (courseArrayList != null)
            return courseArrayList.size();
        else
            return 0;
    }

    public void updateList(ArrayList<Course> newList){
        courseArrayList = newList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView courseIDTV, courseCodeTV, courseNameTV, courseInstructorTV;
        private ItemClick itemClick;

        //Constructor
        public ViewHolder(@NonNull View itemView, ItemClick itemCLick) {
            super(itemView);
            this.itemClick = itemCLick;

            // initializing our text views
            courseIDTV = itemView.findViewById(R.id.idTVcourseID);
            courseCodeTV = itemView.findViewById(R.id.idTVcourseCode);
            courseNameTV = itemView.findViewById(R.id.idTVcourseName);
            courseInstructorTV = itemView.findViewById(R.id.idTVcourseInstructor);

            //attach onClick listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClick != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            itemCLick.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}