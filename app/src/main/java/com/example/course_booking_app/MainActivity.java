package com.example.course_booking_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends CustomActivity {

    //Attribute declarations
    protected Button enter, create;
    protected EditText username, password;
    protected Spinner userType;
    public static DatabaseHandler db;

    //Other field declarations
    protected ArrayList<String> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Preset code
        super.onCreate(savedInstanceState);

        //Set content view
        setContentView(R.layout.activity_main);

        //Initialize attributes
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        enter = findViewById(R.id.enter);
        create = findViewById(R.id.create);
        userType = findViewById(R.id.userType);

        //Initialize userList
        userList = new ArrayList<>();

        //Initialize database/database handler
        db = new DatabaseHandler(this);
        db.addDefaults();

        //Objects to help with the Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_account_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// Specify the layout to use when the list of choices appears
        userType.setAdapter(adapter);// Apply the adapter to the spinner

        //Create action listeners
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch ( db.match(username.getText().toString(), password.getText().toString()) ) {
                    case 0:
                        toast = Toast.makeText(context, "Logging in...", Toast.LENGTH_SHORT);
                        toast.show();

                        //Update the public fields currentUser, currentType
                        currentUser = username.getText().toString();
                        currentType = db.findUserType(currentUser);

                        //Open the correct welcome page
                        if (currentType.equals("administrator"))
                            openAdministratorActivity();
                        else if (currentType.equals("instructor"))
                            openInstructorActivity();
                        else
                            openStudentActivity();
                        break;
                    case 2:
                        toast = Toast.makeText(context, "User not found", duration);
                        toast.show();
                        break;
                    case 4:
                        toast = Toast.makeText(context, "Wrong password", duration);
                        toast.show();
                        break;
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
                switch ( db.addUser(name, pass, type) ) {
                    case 0:
                        toast = Toast.makeText(context, "Account created", duration);
                        toast.show();
                        break;
                    case 2:
                        toast = Toast.makeText(context, "Please select account type", duration);
                        toast.show();
                        break;
                    case 4:
                        toast = Toast.makeText(context, "User already exists", duration);
                        toast.show();
                        break;
                }

            }
        });

    }

}