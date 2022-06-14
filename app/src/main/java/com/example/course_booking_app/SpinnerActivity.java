package com.example.course_booking_app;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    Spinner userType;
    String selectedItem;

    @Override // An item was selected.
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // You can retrieve the selected item using parent.getItemAtPosition(pos)
        userType = findViewById(R.id.userType);
        userType.setOnItemSelectedListener(this);
        selectedItem = parent.getItemAtPosition(pos).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        MainActivity.toast.makeText(MainActivity.context, "Select account type", MainActivity.duration).show();
    }

    public String getSelectedItem(){
        return selectedItem;
    }

}