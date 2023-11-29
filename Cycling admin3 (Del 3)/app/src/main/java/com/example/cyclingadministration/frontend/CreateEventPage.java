package com.example.cyclingadministration.frontend;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Events;
import com.example.cyclingadministration.misc.CustomAdapter2;
import com.example.cyclingadministration.misc.CustomAdapter3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateEventPage extends AppCompatActivity {

    private List<String> items;
    private List<String> itemList;
    Spinner spinner;

    EditText evName, date, maxpple;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_page);

        Events allEvents = new Events();
        items = new ArrayList<>(); // Initialize items list



        submit = findViewById(R.id.submitEvent);
        allEvents.getAllEventTypes(new Events.EventTypesCallback() {
            @Override
            public void onResult(boolean success, List<String> events) {
                if (success) {
                    items = events;
                    Log.d(TAG, items.toString());

                    // Convert List to String array
                    String[] types = new String[items.size()];
                    types = items.toArray(types);

                    spinner = findViewById(R.id.chooseEventType);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(CreateEventPage.this, android.R.layout.simple_spinner_item, types);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                } else {
                    // Handle the case where an error occurred
                    // For now, log a message
                    Log.w(TAG, "Error fetching events.");
                }
            }
        });

        spinner = findViewById(R.id.chooseEventType);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedValue = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected, if needed.
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evName = findViewById(R.id.editTextText2);
                String name = evName.getText().toString();
                date= findViewById(R.id.editTextText3);
                String date2 = date.getText().toString();
                maxpple= findViewById(R.id.editTextText4);
                String max = maxpple.getText().toString();

                Map<String, String> val = new HashMap<>();
                val.put("Name", name);
                val.put("Name", date2);
                val.put("Name", max);

                Events evnt = new Events();
                evnt.addEvent(val);
                Intent intent = new Intent(getApplicationContext(), clubowner_main.class);
                startActivity(intent);
            }
        });
    }


    /*
    public void addFields(String var) {
        // Make sure itemList is initialized
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        Events d = new Events();
        d.getAllEventType(new Events.EventTypeCallback() {
            @Override
            public void onResult(boolean success, List<Map<String, Object>> eventTypes) {
                if (success) {
                    // Iterate through the list of event types and do something with the data
                    for (Map<String, Object> eventType : eventTypes) {
                        // Do something with each eventType
                        for (Map.Entry<String, Object> entry : eventType.entrySet()) {

                            String value = (String) entry.getValue();
                            itemList.add(value);
                        }

                    }
                } else {
                    // Handle the case where an error occurred
                    // For now, log a message
                    Log.w(TAG, "Error fetching events.");
                }
            }
        }, var);



        CustomAdapter3 adapter = new CustomAdapter3(CreateEventPage.this, itemList);
        ListView listView = findViewById(R.id.fieldList);

        // Check if adapter is set before calling setAdapter
        if (listView != null && adapter != null) {
            listView.setAdapter(adapter);
            Log.d(TAG, itemList.toString());
        } else {
            Log.e(TAG, "ListView or adapter is null.");
        }
    }*/



}