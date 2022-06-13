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

    //Declarations
    protected Button back, delete;
    protected TextView textView, usernameDisplay;
    protected boolean doublePressed = false; //this boolean confirms whether the user has confirmed their account deletion by double pressing delete

    private ArrayList<UserModal> userModalArrayList;
    private DatabaseHandler dbHandler;
    private UserRVAdapter userRVAdapter;
    private RecyclerView usersRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        //Initialize widgets
        back = findViewById(R.id.back);
        delete = findViewById(R.id.delete);
        usernameDisplay = findViewById(R.id.usernameDisplay);
        textView = findViewById(R.id.textView);

        //setup
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
                MainActivity.db.removeUser(purgedUser);
                userModalArrayList.remove(pos);
                userRVAdapter.notifyDataSetChanged();

                Context context = getApplicationContext();
                CharSequence text = "Account '" + purgedUser + "' deleted!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        };

        userModalArrayList = new ArrayList<>();
        dbHandler = new DatabaseHandler(AdministratorActivity.this);

        userModalArrayList = dbHandler.getUsers();

        userRVAdapter = new UserRVAdapter(userModalArrayList, AdministratorActivity.this);
        usersRV = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdministratorActivity.this, RecyclerView.VERTICAL, false);
        usersRV.setLayoutManager(linearLayoutManager);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(usersRV);
        usersRV.setAdapter(userRVAdapter);

        //Initialize usernameDisplay
        usernameDisplay.setText("logged in as: " + MainActivity.currentUser);

        //Create action listeners
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Goes back to MainActivity.java
                openMain();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (doublePressed == true) {
                    textView.setText("Account deleted");
                    System.out.println( (MainActivity.db).removeUser(MainActivity.currentUser) );
                    doublePressed = false;
                    openMain();
                } else {
                    textView.setText("Press again to confirm");
                    doublePressed = true;
                }
            }
        });


    }

    //Re-opens main page
    protected void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}