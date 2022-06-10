package com.example.course_booking_app;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
/*
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
*/
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    //Widget declarations
    Button enter;
    EditText username, password;
    ListView userListView;

    //Other field declarations
    //String u, p;
    ArrayList<String> userList;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Preset code
        super.onCreate(savedInstanceState);

        //Set content view
        setContentView(R.layout.activity_main);

        //Initialize widgets
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        userListView = findViewById(R.id.userListView);
        enter = findViewById(R.id.enter);

        //Initialize userList
        userList = new ArrayList<>();

        //Initialize database handler
        UserData db = new UserData(this);

        //Add preset users to the database
        db.addUser("admin", "admin123");

        //Create action listener
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pass = password.getText().toString();

                Toast.makeText(MainActivity.this, "add user", Toast.LENGTH_SHORT).show();
                if (db.findPassword(name) == null) {//No password associated with this user, i.e. user doesn't exist
                    //Display error message (can't find user)
                } else if (db.findPassword(name) == pass) {//Password matches username
                    //Move to next screen
                } else {//User exists but password is incorrect
                    //Display error message (password incorrect)
                }
            }
        });

        //View database data
        viewData(db);

    }

    //For viewing database data
    private void viewData(UserData db){
        Cursor cursor = db.getData();

        if (cursor == null) {
            return;
        }

        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "No data", Toast.LENGTH_SHORT);
        } else {
            while (cursor.moveToNext()) {
                userList.add(cursor.getString(1));
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        userListView.setAdapter(adapter);
    }

}