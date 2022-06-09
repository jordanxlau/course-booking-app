package com.example.course_booking_app;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.course_booking_app.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    //Widget declarations
    Button enter;
    EditText username, password;
    ListView userListView;

    //Other field declarations
    String u, p;//
    ArrayList<String> userList;
    ArrayAdapter<String> adapter;//Should be String

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Preset code
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //Initialize widgets and action listeners
        enter = findViewById(R.id.enter);
        enter.setOnClickListener(this);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        userListView = findViewById(R.id.userListView);

        //Initialize database handler
        UserData db = new UserData(this);
        viewData(db);

        //Initialize productList
        userList = new ArrayList<String>();
    }

    //For viewing database data
    private void viewData(UserData db){
        Cursor cursor = db.getData();
        if (cursor.getCount() == 0)
            Toast.makeText(MainActivity.this, "No data", Toast.LENGTH_SHORT);
        else
            while(cursor.moveToNext()){
                userList.add( cursor.getString(1) );
            }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
            userListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.enter){
            u = username.getText().toString();
            p = password.getText().toString();
            Toast.makeText(MainActivity.this, "add user", Toast.LENGTH_SHORT).show();
            //Change view to logged in screen
        }
    }
}