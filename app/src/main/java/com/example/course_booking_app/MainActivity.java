package com.example.course_booking_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    //Widget/Attribute declarations
    Button enter, create;
    EditText username, password;
    public static TextView message;
    Spinner userType;
    ListView list;
    ArrayAdapter adapter;


    //Other field declarations
    ArrayList<String> userList;

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
        userType = findViewById(R.id.userType);
        list = findViewById(R.id.list);

        //Initialize userList
        userList = new ArrayList<>();

        //Initialize database handler
        UserData db = new UserData(this);

        //Add preset users to the database
        db.addUser("admin", "admin123", "admin");

        //Write default message
        //Toast.makeText(MainActivity.this, "Enter Password and Username", Toast.LENGTH_SHORT);

        //Objects to help with the Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_account_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// Specify the layout to use when the list of choices appears
        userType.setAdapter(adapter);// Apply the adapter to the spinner


        //Create action listeners
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEntered = username.getText().toString();
                String actualPass = password.getText().toString();
                String foundPass = db.findPassword(userEntered);//the password of the username entered

                //Toast.makeText(MainActivity.this, "add user", Toast.LENGTH_SHORT).show();
                if (foundPass == null) {//No password associated with this user, i.e. user doesn't exist
                    //Display error message (can't find user)
                    message.setText("can't find user");
                } else if (foundPass.equals(actualPass)) {//Password matches username
                    //Move to next screen
                    message.setText("found user");
                    String userType = db.findUserType(userEntered);
                    openAdministratorActivity();

                } else {//User exists but password is incorrect
                    //Display error message (password incorrect)
                    message.setText("wrong password");
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add a user of the correct user account type to the database
                System.out.println("TRACE CREATE");
                String name = username.getText().toString();
                String pass = password.getText().toString();
                String type = userType.getSelectedItem().toString();
                System.out.println(name + " " + pass + " " + type);
                db.addUser(name, pass, type);
            }
        });

        //View database data
        viewData(db);

    }

    //Opens administrator activity window
    public void openAdministratorActivity(){
        Intent intent = new Intent(this, AdministratorActivity.class);
        startActivity(intent);
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
                userList.add(cursor.getString(1) + "     "  + cursor.getString(2));
            }
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, userList);
        list.setAdapter(adapter);
    }

}