package com.example.course_booking_app;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<UserModal> userModalArrayList;
    private Context context;

    // constructor
    public UserRVAdapter(ArrayList<UserModal> userModalArrayList, Context context) {
        this.userModalArrayList = userModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        UserModal modal = userModalArrayList.get(position);
        holder.userIDTV.setText(modal.getID());
        holder.userNameTV.setText(modal.getUsername());
        holder.userPasswordTV.setText("account type:");
        holder.userTypeTV.setText(modal.getUsertype());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return userModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView userIDTV, userNameTV, userPasswordTV, userTypeTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            userIDTV = itemView.findViewById(R.id.idTVuserID);
            userNameTV = itemView.findViewById(R.id.idTVuserName);
            userPasswordTV = itemView.findViewById(R.id.idTVuserPassword);
            userTypeTV = itemView.findViewById(R.id.idTVuserType);

        }
    }

    public interface OnUserListener{
        void onUserClick(int position);
    }

}