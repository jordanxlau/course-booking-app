package com.example.course_booking_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdministratorActivity extends AppCompatActivity{

    //Widget Declarations
    protected Button back;
    protected Button viewCourses;

    protected TextView usernameDisplay;
//
//    protected Button delete;
//    protected boolean doublePressed = false; //this boolean confirms whether the user has confirmed their account deletion by double pressing delete

    //Declarations for toast
    public int duration = Toast.LENGTH_LONG;
    public static Toast toast;
    public Context context;

    //Other declarations
    private ArrayList<UserModal> userModalArrayList;
    private UserRVAdapter userRVAdapter;
    private RecyclerView usersRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        //Initialize widgets
        back = findViewById(R.id.back);
//        delete = findViewById(R.id.delete);
        usernameDisplay = findViewById(R.id.usernameDisplay);
        viewCourses = findViewById(R.id.viewCourses);

        //Initialize context
        context = getApplicationContext();

        //Item Touch Helper setup
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos;
                String purgedUser;

                pos = viewHolder.getAdapterPosition();
                purgedUser = userModalArrayList.get(pos).getUsername();

                if(purgedUser.equals("admin")){
                    toast = Toast.makeText(context, "Default admin account cannot be deleted!", duration);
                    toast.show();
                    userRVAdapter.notifyDataSetChanged();
                }
                else{
                    toast = Toast.makeText(context, "Account '" + purgedUser + "' deleted!", duration);
                    toast.show();
                    MainActivity.db.removeUser(purgedUser);
                    userModalArrayList.remove(pos);
                    userRVAdapter.notifyDataSetChanged();
                }

            }
        };

        //Other setup
        userModalArrayList = new ArrayList<>();

        userModalArrayList = MainActivity.db.getUsers();

        userRVAdapter = new UserRVAdapter(userModalArrayList, AdministratorActivity.this);
        usersRV = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdministratorActivity.this, RecyclerView.VERTICAL, false);
        usersRV.setLayoutManager(linearLayoutManager);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(usersRV);
        usersRV.setAdapter(userRVAdapter);

        //Initialize usernameDisplay
        usernameDisplay.setText("logged in as: " + MainActivity.currentUser);
        System.out.println("print");

        //Create action listeners
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Goes back to MainActivity.java
                openMain();
            }
        });

        viewCourses.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println("TRACE");
                openCourses();
            }
        });
    }

    //Re-opens main page
    protected void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    protected void openCourses(){
        Intent intent = new Intent(this, CoursesActivity.class);
        startActivity(intent);
    }

}