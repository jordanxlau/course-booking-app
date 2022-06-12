package com.example.course_booking_app;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    //Widget declarations
    Button enter, create;
    EditText username, password;
    TextView message;

    //Other field declarations
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
        message = findViewById(R.id.message);
        enter = findViewById(R.id.enter);
        create = findViewById(R.id.create);

        //Initialize userList
        userList = new ArrayList<>();

        //Initialize database handler
        UserData db = new UserData(this);

        //Add preset users to the database
        db.addUser("admin", "admin123");

        //Write default message
        //Toast.makeText(MainActivity.this, "Enter Password and Username", Toast.LENGTH_SHORT);

        //Create action listeners
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String actualPass = password.getText().toString();
                String foundPass = db.findPassword(name);//the password of the username entered

                //Toast.makeText(MainActivity.this, "add user", Toast.LENGTH_SHORT).show();
                if (foundPass == null) {//No password associated with this user, i.e. user doesn't exist
                    //Display error message (can't find user)
                    message.setText("can't find user");
                } else if (foundPass.equals(actualPass)) {//Password matches username
                    //Move to next screen
                    message.setText("found user");
                } else {//User exists but password is incorrect
                    //Display error message (password incorrect)
                    message.setText("wrong password");
                }
                System.out.println("Trace: " + foundPass);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            message.setText("No data");
            //Toast.makeText(MainActivity.this, "No data", Toast.LENGTH_SHORT);
        } else {
            while (cursor.moveToNext()) {
                userList.add(cursor.getString(1));
            }
        }

        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        //message.setAdapter(adapter);
    }

}