package com.example.course_booking_app;

import android.content.Context;
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
    protected Button enter, create;
    protected EditText username, password;
    public static TextView message;//For welcome messages
    protected Spinner userType;
    protected ListView list;
    protected ArrayAdapter adapter;
    public static DatabaseHandler db;

    //Toast declarations
    public static int duration = Toast.LENGTH_LONG;
    public static Toast toast;//for error messages
    public static Context context;

    //Other field declarations
    protected ArrayList<String> userList;
    public static String currentUser = "";

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

        //Initialize context
        context = getApplicationContext();

        //Initialize userList
        userList = new ArrayList<>();

        //Initialize database handler
        db = new DatabaseHandler(this);

        //Add preset users to the database
        db.addUser("admin", "admin123", "administrator");

        //Initialize message
        message.setText("uOttawa Course Portal login");

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

                if (foundPass == null) {//No password associated with this user, i.e. user doesn't exist
                    //Display error message (can't find user)
                    toast = Toast.makeText(context, "User not found.", duration);
                    toast.show();
                } else if (foundPass.equals(actualPass)) {//Password matches username
                    //Move to next screen
                    toast = Toast.makeText(context, "Logging in...", Toast.LENGTH_SHORT);
                    toast.show();
                    //Update the public field currentUser
                    currentUser = userEntered;
                    //Open the correct welcome page
                    String typeEntered = db.findUserType(userEntered);
                    if(typeEntered.equals("administrator")){
                        openAdministratorActivity();
                    } else if(typeEntered.equals("instructor")){
                        openInstructorActivity();
                    } else {
                        openStudentActivity();
                    }

                } else {//User exists but password is incorrect
                    //Display error message (password incorrect)
                    toast = Toast.makeText(context, "Wrong password!", duration);
                    toast.show();
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add a user of the correct user account type to the database
                String name = username.getText().toString();
                String pass = password.getText().toString();
                String type = userType.getSelectedItem().toString();
                if ( db.addUser(name, pass, type) ) {
                    toast = Toast.makeText(context, "Account created.", duration);
                    toast.show();
                }
            }
        });

/*        //View database data
        viewData(db);*/

        //Temporary accounts to be removed
        db.removeUser("adfg");
        db.removeUser("hgfds");
    }

    //Opens admin welcome page
    protected void openAdministratorActivity(){
        Intent intent = new Intent(this, AdministratorActivity.class);
        startActivity(intent);
    }

    //Opens instructor welcome page
    protected void openInstructorActivity(){
        Intent intent = new Intent(this, InstructorActivity.class);
        startActivity(intent);
    }

    //Opens student welcome page
    protected void openStudentActivity(){
        Intent intent = new Intent(this, StudentActivity.class);
        startActivity(intent);
    }
    

}